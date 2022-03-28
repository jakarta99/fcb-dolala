package tw.com.fcb.dolala.core.ir.service;

import java.time.LocalDate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.fcb.dolala.core.ir.repository.IRMasterRepository;
import tw.com.fcb.dolala.core.common.repository.SerialNumberRepository;

import tw.com.fcb.dolala.core.ir.repository.entity.IRMaster;

import tw.com.fcb.dolala.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.dolala.core.ir.web.dto.IR;


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
	SerialNumberRepository serialNumberRepository;

	private final String  systemType = "IR";
	private final String  noCode = "S";
	// 新增匯入匯款主檔
	public String insertIRMaster(IRSaveCmd saveCmd) throws Exception {
		IRMaster irMaster = new IRMaster();
		// 自動將saveCmd的屬性，對應到entity裡
		BeanUtils.copyProperties(saveCmd, irMaster);

		irMasterRepository.save(irMaster);

		return irMaster.getIrNo();
	}
    
    //傳入匯入匯款編號查詢案件
	public IR findOne(String irNo) {
		IR ir = new IR();
//		IRMaster irMaster = repository.findByIrNo(irNo);
//		if (irMaster != null) {
//			// 自動將entity的屬性，對應到dto裡
//			BeanUtils.copyProperties(irMaster, ir);
//		}
		IRMaster irMaster = irMasterRepository.findByIrNo(irNo).orElse(new IRMaster());
		BeanUtils.copyProperties(irMaster, ir);
		return ir;
	}
    
	//傳入受通知單位查詢案件數
	public Integer getIrCaseCount(String branch) {
		Integer count = 0;
		count = irMasterRepository.findByBeAdvBranchAndPrintAdvMk(branch,"N").size();
		return count;
	}
	
	//print 列印通知書
	public void print(String irNo) {
    	IR ir = this.findOne(irNo); 
    	
    	if (!(ir == null))
    	{
    		ir.setPrintAdvMk("Y");
    		ir.setPrintAdvDate(LocalDate.now());    	
    		this.updateMaster(ir);    		
    	}
    }
    
	//settle 解款
    public void settle(String irNo) {
    	IR ir = this.findOne(irNo); 
    	
    	if (!(ir == null))
    	{
    		ir.setPaidStats(2);    	
    		this.updateMaster(ir);
    	}
    }
    
    public void updateMaster(IR ir) {
    	
	    IRMaster irMaster = new IRMaster();
	    BeanUtils.copyProperties(ir, irMaster);
	    irMasterRepository.save(irMaster);
    	
    }
}
