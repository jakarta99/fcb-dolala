package tw.com.fcb.dolala.core.ir.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.fcb.dolala.core.common.service.CustomerAccountService;
import tw.com.fcb.dolala.core.common.service.CustomerService;
import tw.com.fcb.dolala.core.common.web.dto.BankDto;
import tw.com.fcb.dolala.core.common.web.dto.Customer;
import tw.com.fcb.dolala.core.common.web.dto.CustomerAccount;
import tw.com.fcb.dolala.core.ir.http.CommonFeignClient;
import tw.com.fcb.dolala.core.ir.repository.IRCaseRepository;
import tw.com.fcb.dolala.core.ir.repository.entity.IRCaseEntity;
import tw.com.fcb.dolala.core.ir.vo.IRCaseVo;
import tw.com.fcb.dolala.core.ir.web.cmd.SwiftMessageSaveCmd;
import tw.com.fcb.dolala.core.ir.web.dto.IRCase;

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
    CustomerAccountService customerAccountService;
    @Autowired
    CustomerService customerService;
    @Autowired
    CommonFeignClient commonFeignClient;
    //取號檔 SystemType,branch
    private final String systemType = "IR_SEQ";
    private final String branch = "999";


    public boolean insert(IRCaseVo irCaseVo){
        //beginTx

        IRCaseEntity irCaseEntity = new IRCaseEntity();
// 將irCaseVo，對應到entity裡
        BeanUtils.copyProperties(irCaseVo, irCaseEntity);
        irCaseRepository.save(irCaseEntity);
        //commitTx
        return true;
    }

    public IRCaseVo  saveIRCaseData(IRCaseVo irCaseVo){

        //顧客資料，受通知分行
        Customer customer = commonFeignClient.getCustomer(irCaseVo.getReceiverAccount());
        irCaseVo.setBeAdvBranch(customer.getBranchID());
        irCaseVo.setCustomerID(customer.getCustomerId());

       //讀取匯率
        BigDecimal rate = commonFeignClient.getFxRate("B",irCaseVo.getCurrency(),"TWD");

        //讀取銀行名稱地址

        BankDto bankDto = commonFeignClient.getBank(irCaseVo.getSenderSwiftCode());
        irCaseVo.setSenderInfo1(bankDto.getName());
        irCaseVo.setSenderInfo3(bankDto.getAddress());

        //讀取都市檔

        //讀取存匯行關係

        //讀取是否為同存行

        //取號
        String irSeqNo = commonFeignClient.getSeqNo(systemType,branch);

        irCaseVo.setSeqNo(irSeqNo);

        return irCaseVo;
    }

    //傳入seqNo編號查詢案件
    public IRCase getByIRSeqNo(String irSeqNo) {

        IRCaseEntity irCaseEntity = irCaseRepository.findBySeqNo(irSeqNo);

        IRCase irCase = new IRCase();

        if (irCase != null) {
            // 自動將entity的屬性，對應到dto裡
            BeanUtils.copyProperties(irCaseEntity, irCase);
        }
        return irCase;
    }

}