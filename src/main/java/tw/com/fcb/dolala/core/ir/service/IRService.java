package tw.com.fcb.dolala.core.ir.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.fcb.dolala.core.ir.repository.IRMasterRepository;
import tw.com.fcb.dolala.core.ir.repository.entity.ExchgRate;
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
@Transactional
@Service
public class IRService {
    @Autowired
    IRMasterRepository repository;
    @Autowired
    ExchgRateService rateService;

	// 新增匯入匯款主檔
	public void insert(IRSaveCmd saveCmd) {
		IRMaster irMaster = new IRMaster();
		// 自動將saveCmd的屬性，對應到entity裡
		BeanUtils.copyProperties(saveCmd, irMaster);
		// 從匯率資料檔取得ExchgRate
		irMaster.setExchangeRate(rateService.getRate(ExchgRate.EXCHG_RATE_TYPE_BUY, irMaster.getCurency(), "TWD"));
		repository.save(irMaster);
	}
    
    //傳入匯入匯款編號查詢案件
	public IR findOne(String irNo) {
		IR ir = new IR();
		IRMaster irMaster = repository.findByIrNo(irNo);
		if (irMaster != null) {
			BeanUtils.copyProperties(irMaster, ir);
		}
		return ir;
	}
    
	//傳入受通知單位查詢案件數
	public Integer getIrCaseCount(String branch) {
		Integer count = 0;
		count = repository.findByBeAdvBranch(branch).size();
		return count;
	}
}
