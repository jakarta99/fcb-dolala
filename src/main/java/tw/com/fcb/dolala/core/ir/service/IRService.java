package tw.com.fcb.dolala.core.ir.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.fcb.dolala.core.common.repository.entity.ExchgRate;
import tw.com.fcb.dolala.core.ir.http.CommonFeignClient;
import tw.com.fcb.dolala.core.ir.repository.IRMasterRepository;

import tw.com.fcb.dolala.core.ir.repository.entity.IRMaster;

import tw.com.fcb.dolala.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.dolala.core.ir.web.dto.IRAdvicePrintListDto;
import tw.com.fcb.dolala.core.ir.web.dto.IRDto;


/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: IRService
 * Author: sinjen
 * Date: 2022/3/10 下午 03:32
 * Description: 匯入匯款service
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@Slf4j
@Transactional
@Service
public class IRService {
    @Autowired
    IRMasterRepository irMasterRepository;

	@Autowired
	CommonFeignClient commonFeignClient;

	private final String  systemType = "IRDto";
	private final String  noCode = "S";
	// 新增匯入匯款主檔
	public IRMaster insertIRMaster(IRSaveCmd irSaveCmd) throws Exception {
		IRMaster irMaster = new IRMaster();
		// 自動將saveCmd的屬性，對應到entity裡
		BeanUtils.copyProperties(irSaveCmd, irMaster);
		irSaveCmd.setExchangeRate(commonFeignClient.getFxRate(ExchgRate.EXCHG_RATE_TYPE_BUY, irSaveCmd.getCurrency(),"TWD"));
		//取號
		irSaveCmd.setIrNo(commonFeignClient.getFxNo(noCode,systemType,irSaveCmd.getBeAdvBranch()));
		irMasterRepository.save(irMaster);

		return irMaster;
	}
    
    //傳入匯入匯款編號查詢案件
	public IRDto findOne(String irNo) {
		IRDto irDto = new IRDto();
//		IRMaster irMaster = repository.findByIrNo(irNo);
//		if (irMaster != null) {
//			// 自動將entity的屬性，對應到dto裡
//			BeanUtils.copyProperties(irMaster, irDto);
//		}
		IRMaster irMaster = irMasterRepository.findByIrNo(irNo).orElse(new IRMaster());
		BeanUtils.copyProperties(irMaster, irDto);
		return irDto;
	}
    
	//傳入受通知單位查詢案件數
	public Integer getIrCaseCount(String branch) {
		Integer count = 0;
		count = irMasterRepository.findByBeAdvBranchAndPrintAdvMk(branch,"N").size();
		return count;
	}
	
	//print 列印通知書
	public void print(String irNo) {
    	IRDto irDto = this.findOne(irNo);
    	
    	if (!(irDto == null))
    	{
    		irDto.setPrintAdvMk("Y");
    		irDto.setPrintAdvDate(LocalDate.now());
    		this.updateMaster(irDto);
    	}
    }
    
	//settle 解款
    public void settle(String irNo) {
    	IRDto irDto = this.findOne(irNo);
    	
    	if (!(irDto == null))
    	{
    		irDto.setPaidStats(2);
    		this.updateMaster(irDto);
    	}
    }
    
    public void updateMaster(IRDto irDto) {
    	
	    IRMaster irMaster = new IRMaster();
	    BeanUtils.copyProperties(irDto, irMaster);
	    irMasterRepository.save(irMaster);
    	
    }
    
    //S131R 「處理種類」為(0、1、2、7或8) 之發查電文。 ==>進行通知書列印(多筆)
   	public List<IRMaster> qryAdvicePrint(String branch)
   	{
   		List<IRMaster> listData = new ArrayList<IRMaster>();
   		listData = irMasterRepository.findByBeAdvBranchAndPrintAdvMk(branch, "N");
   		IRMaster irMaster;
   		
   		for (int i = 0; i < listData.size(); i ++)
   		{
   			//update IRMaster PrintAdvMk = Y ：已列印通知書
   			irMaster = listData.get(i);
   			irMaster.setPrintAdvMk("Y");
   			irMasterRepository.save(irMaster);
   		}
   		
   		return listData;
   	}
   		
   	// S131I1 "「處理種類」為(3或4) 之發查電文。==>回傳「受通知筆數」、「已印製通知書筆數」欄位"
   	public int[] qryAdviceCount(String branch)
   	{
   		int[] adviceCount = new int[2];
   		
   		//「受通知筆數」
   		List<IRMaster> listData = new ArrayList<IRMaster>();
   		listData = irMasterRepository.findByBeAdvBranch(branch);
   		
   		if (listData != null)
   			adviceCount[0] = listData.size();
   		
   		//「已印製通知書筆數」
   		listData = irMasterRepository.findByBeAdvBranchAndPrintAdvMk(branch, "Y");
   		
   		if (listData != null)
   			adviceCount[1] = listData.size();
   		
   		return adviceCount;
   	}
   	
   	// S131I2 "「處理種類」為(5或6) 之發查電文。==>回傳S1311畫面"
   	public List<IRAdvicePrintListDto> qryAdviceList(String branch)
   	{
   		List<IRMaster> listData = new ArrayList<IRMaster>();
   		List<IRAdvicePrintListDto> i2ListData = new ArrayList<IRAdvicePrintListDto>();
   		listData = irMasterRepository.findByBeAdvBranch(branch);
   		IRMaster irMaster;
   		IRAdvicePrintListDto irS131I2;
   		
   		for (int i = 0; i < listData.size(); i ++)
   		{			
   			irS131I2 = new IRAdvicePrintListDto();
   			irMaster = listData.get(i);
   			BeanUtils.copyProperties(irMaster, irS131I2);
   			i2ListData.add(irS131I2);
   		}
   		
   		return i2ListData;
   	}

}
