package tw.com.fcb.dolala.core.ir.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import tw.com.fcb.dolala.core.ir.repository.IRMasterRepository;
import tw.com.fcb.dolala.core.ir.repository.entity.IRMaster;

@Slf4j
@Transactional
@Service
public class IRAdvicePrintS131Service {

	@Autowired
	IRMasterRepository irMasterRepository;

	
	//S131R 「處理種類」為(0、1、2、7或8) 之發查電文。 ==>進行通知書列印(多筆)
	public List<IRMaster> qryAdvicePrint(String branch)
	{
		List<IRMaster> listData = new ArrayList<IRMaster>();
		listData = irMasterRepository.findByBeAdvBranchAndPrintAdvMk(branch, "N");
		return listData;
	}
	
	// S131I1 "「處理種類」為(3或4) 之發查電文。==>回傳「受通知筆數」、「已印製通知書筆數」欄位"
	public int[] qryAdviceCount(String branch)
	{
		int[] adviceCount = new int[2];
		
		List<IRMaster> listData = new ArrayList<IRMaster>();
		listData = irMasterRepository.findByBeAdvBranch(branch);
		
		if (listData != null)
			adviceCount[0] = listData.size();
		
		listData = irMasterRepository.findByBeAdvBranchAndPrintAdvMk(branch, "Y");
		
		if (listData != null)
			adviceCount[1] = listData.size();
		
		return adviceCount;
	}
}
