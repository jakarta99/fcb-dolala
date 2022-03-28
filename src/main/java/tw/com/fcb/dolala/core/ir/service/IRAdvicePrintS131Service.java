package tw.com.fcb.dolala.core.ir.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import tw.com.fcb.dolala.core.ir.repository.IRMasterRepository;
import tw.com.fcb.dolala.core.ir.repository.entity.IRMaster;
import tw.com.fcb.dolala.core.ir.web.dto.IRS131I2;

@Slf4j
@Transactional
@Service
public class IRAdvicePrintS131Service {

	@Autowired
	IRMasterRepository irMasterRepository;
	
	IRMaster irMaster;
	IRS131I2 irS131I2;
	
	//S131R 「處理種類」為(0、1、2、7或8) 之發查電文。 ==>進行通知書列印(多筆)
	public List<IRMaster> qryAdvicePrint(String branch)
	{
		List<IRMaster> listData = new ArrayList<IRMaster>();
		listData = irMasterRepository.findByBeAdvBranchAndPrintAdvMk(branch, "N");
		
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
	public List<IRS131I2> qryAdviceList(String branch)
	{
		List<IRMaster> listData = new ArrayList<IRMaster>();
		List<IRS131I2> i2ListData = new ArrayList<IRS131I2>();
		listData = irMasterRepository.findByBeAdvBranch(branch);
		
		for (int i = 0; i < listData.size(); i ++)
		{			
			irS131I2 = new IRS131I2();
			irMaster = listData.get(i);
			BeanUtils.copyProperties(irMaster, irS131I2);
			i2ListData.add(irS131I2);
		}
		
		return i2ListData;
	}
	
}
