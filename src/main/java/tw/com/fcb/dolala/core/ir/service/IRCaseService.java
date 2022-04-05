package tw.com.fcb.dolala.core.ir.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.fcb.dolala.core.common.web.dto.BankDto;
import tw.com.fcb.dolala.core.common.web.dto.CustomerDto;
import tw.com.fcb.dolala.core.ir.http.CommonFeignClient;
import tw.com.fcb.dolala.core.ir.repository.IRCaseRepository;
import tw.com.fcb.dolala.core.ir.repository.IRMasterRepository;
import tw.com.fcb.dolala.core.ir.repository.entity.IRCaseEntity;
import tw.com.fcb.dolala.core.ir.repository.entity.IRMaster;
import tw.com.fcb.dolala.core.ir.vo.IRCaseVo;
import tw.com.fcb.dolala.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.dolala.core.ir.web.dto.IRCaseDto;
import tw.com.fcb.dolala.core.ir.web.dto.IRDto;

import java.math.BigDecimal;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: IRCaseService
 * Author: Han-Ru
 * Date: 2022/3/11 下午 05:30
 * Description: 匯入電文service
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@Transactional
@Service
public class IRCaseService {
    @Autowired
    IRCaseRepository irCaseRepository;
    @Autowired
    CommonFeignClient commonFeignClient;
    @Autowired
    IRMessageCheckSerivce irMessageCheckSerivce;
    @Autowired
	IRMasterRepository irMasterRepository;

    @Autowired
    IRService irService;
    //取號檔 SystemType,branch
    private final String systemType = "IR_SEQ";
    private final String branch = "999";


    public IRCaseDto irCaseInsert(IRCaseDto irCaseDto) throws Exception {
        //beginTx

        IRCaseEntity irCaseEntity = new IRCaseEntity();
// 將irCaseVo，對應到entity裡
        BeanUtils.copyProperties(irCaseDto, irCaseEntity);
        irCaseRepository.save(irCaseEntity);
  // check 期交所案件
        boolean checkFE = checkFuturesExchange(irCaseDto.getReceiverAccount());
        if (checkFE == true){
            irCaseDto = beFEUpdateIRCase(irCaseDto.getSeqNo());
            IRMaster irMaster = beFEAutoSettle(irCaseDto.getSeqNo());
        }
        return irCaseDto;
        //commitTx

    }


    public IRCaseDto beFEUpdateIRCase(String irSeqNo) throws Exception {
        IRCaseEntity irCaseEntity = new IRCaseEntity();
        IRCaseDto irCaseDto = getByIRSeqNo(irSeqNo);
        irCaseDto.setAutoPassMk("Y");
        irCaseDto.setProcessStatus("7");
        BeanUtils.copyProperties(irCaseDto,irCaseEntity);
        irCaseRepository.save(irCaseEntity);
        return irCaseDto;

    }


    public IRCaseDto setIRCaseData(IRCaseDto irCaseDto) throws Exception {
        // STATUS 七日檔初始狀態
        //      1 ：初值
        //      2 ：印製放行工作單訖(經辦放行) (S111交易)
        irCaseDto.setProcessStatus("1");
        irCaseDto.setAutoPassMk("N");
        // check account
        String receiveAccount = irCaseDto.getReceiverAccount();
        String accountNo = irMessageCheckSerivce.getAccountNo(receiveAccount);
        irCaseDto.setReceiverAccount(accountNo);
        //顧客資料，受通知分行
        CustomerDto customer = commonFeignClient.getCustomer(irCaseDto.getReceiverAccount());
        irCaseDto.setBeAdvBranch(customer.getBranchID());
        irCaseDto.setCustomerID(customer.getCustomerId());
        // check currency

       //讀取匯率
        BigDecimal rate = commonFeignClient.getFxRate("B",irCaseDto.getCurrency(),"TWD");

        //讀取銀行名稱地址
        BankDto bankDto = commonFeignClient.getBank(irCaseDto.getSenderSwiftCode());
        irCaseDto.setSenderInfo1(bankDto.getName());
        irCaseDto.setSenderInfo3(bankDto.getAddress());

        //讀取都市檔
        String country = commonFeignClient.getCountryName(irCaseDto.getSenderSwiftCode().substring(4,5));
        //讀取存匯行關係
        String  isCorrspondent = bankDto.getIsCorrespondent();
        //讀取是否為同存行

        //取號
        String irSeqNo = commonFeignClient.getSeqNo(systemType,branch);

        irCaseDto.setSeqNo(irSeqNo);

        return irCaseDto;
    }

	// 傳入seqNo編號查詢案件
	// s121i 查詢待放行資料
	// S061I 查詢待退匯(無匯入編號)
    public IRCaseDto getByIRSeqNo(String irSeqNo) throws Exception {

        IRCaseEntity irCaseEntity = irCaseRepository.findBySeqNo(irSeqNo).orElseThrow(() -> new Exception("S001"));
        IRCaseDto irCaseDto = new IRCaseDto();

        if (irCaseDto != null) {
            // 自動將entity的屬性，對應到dto裡
            BeanUtils.copyProperties(irCaseEntity, irCaseDto);
        }
        return irCaseDto;
    }

	// 傳入seqNo編號及處理狀態查詢電文檔案件
	public IRCaseEntity getByIRSeqNoAndProcessStatus(String seqNo, String processStatus) throws Exception {

		IRCaseEntity irCaseEntity = irCaseRepository.findBySeqNoAndProcessStatus(seqNo, processStatus).orElseThrow(() -> new Exception("S001"));
		return irCaseEntity;
	}

    public boolean checkFuturesExchange(String account){
        if (account.equals("09300123456"))
            return true;
        else {
            return  false;
        }
    }
    // 期交所自動解款,寫入IRCase,IRMaster
    public IRMaster  beFEAutoSettle(String irSeqNo) throws Exception {
        IRCaseEntity irCaseEntity = new IRCaseEntity();
        IRCaseDto irCaseDto = getByIRSeqNo(irSeqNo);
        irCaseDto.setProcessStatus("7");
        BeanUtils.copyProperties(irCaseDto,irCaseEntity);
        // update processStatus 7 = 已作帳
        irCaseRepository.save(irCaseEntity);

        //自動放行新增進irMaster並更新為已入帳
        IRSaveCmd irSaveCmd = new IRSaveCmd();
        BeanUtils.copyProperties(irCaseEntity,irSaveCmd);
        irSaveCmd = irService.setIRMaster(irSaveCmd);
        //期交所自動解款，PAID_STATUS = 2
        irSaveCmd.setPaidStats(2);
        irSaveCmd.setBeAdvBranch("091");
        irSaveCmd.setProcessBranch("091");
        IRMaster irMaster = irService.insertIRMaster(irSaveCmd);
        return irMaster;
    }

    public boolean updateByIRSeqNo(IRCaseDto irCaseDto) throws Exception{
        IRCaseEntity irCaseEntity = new IRCaseEntity();
// 將irCaseDto，對應到entity裡
        BeanUtils.copyProperties(irCaseDto, irCaseEntity);
        irCaseRepository.save(irCaseEntity);
        return  true;
    }

    // 查詢待處理案件
    public IRCaseDto getByIRSeqNoAndStatus(String seqNo) throws Exception {

    	IRCaseDto irCaseDto = new IRCaseDto();
    	IRCaseEntity irCaseEntity = this.getByIRSeqNoAndProcessStatus(seqNo, "1");
    	if(irCaseEntity != null) {
    		BeanUtils.copyProperties(irCaseEntity, irCaseDto);
    	}
    	return irCaseDto;
    }

    // S061A 執行退匯
    public IRCaseDto exeReturnIRCase(String seqNo) throws Exception {

    	IRCaseDto irCaseDto = new IRCaseDto();
    	IRCaseEntity irCaseEntity = this.getByIRSeqNoAndProcessStatus(seqNo, "1");

    	if(irCaseEntity != null) {
    		irCaseEntity.setProcessStatus("8");
    		// 更新電文檔
    		irCaseRepository.save(irCaseEntity);
    		BeanUtils.copyProperties(irCaseEntity, irCaseDto);
    	} else {
    		// 查無此電文
    		new Exception("S001");
    	}
    	return irCaseDto;
    }

	// S061D 執行退匯刪除
	public IRCaseDto deleteReturnIRCase(String seqNo) throws Exception {

		IRCaseDto irCaseDto = new IRCaseDto();
		IRCaseEntity irCaseEntity = this.getByIRSeqNoAndProcessStatus(seqNo, "8");

		if (irCaseEntity != null) {
			irCaseEntity.setProcessStatus("1");
			// 更新電文檔
			irCaseRepository.save(irCaseEntity);
			BeanUtils.copyProperties(irCaseEntity, irCaseDto);
		} else {
			// 查無此電文
			new Exception("S001");
		}
		return irCaseDto;
	}

 	// S121A 執行MT103放行
	public IRDto exeCaseAuthorization(String seqNo) throws Exception {

		IRDto irDto = new IRDto();
		IRMaster irMaster = null;
		IRCaseEntity irCaseEntity = this.getByIRSeqNoAndProcessStatus(seqNo, "1");

		if (irCaseEntity != null) {
			// 從電文檔搬移到主檔
			irMaster = new IRMaster();
			irMaster.setPaidStats(0);
			irMaster.setValueDate(irCaseEntity.getValueDate());
			irMaster.setIrAmt(irCaseEntity.getIrAmount());
			irMaster.setCurrency(irCaseEntity.getCurrency());
			irMaster.setBeAdvBranch("093");
			irMaster.setPrintAdvMk("Y");
			// 產生外匯編號
			irMaster.setIrNo(commonFeignClient.getFxNo("S", "IRDto", "093"));
			// 新增主檔
			irMasterRepository.save(irMaster);

			// update IRCaseEntity PROCESS_STATUS = 3 ：放行訖
			irCaseEntity.setProcessStatus("3");
			irCaseRepository.save(irCaseEntity);

			// 自動將entity的屬性，對應到dto裡
			BeanUtils.copyProperties(irMaster, irDto);
		} else {
			// 查無此電文
			new Exception("S001");
		}
		return irDto;
	}

}