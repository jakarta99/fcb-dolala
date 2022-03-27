package tw.com.fcb.dolala.core.ir.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import tw.com.fcb.dolala.core.ir.repository.entity.IRMaster;
import tw.com.fcb.dolala.core.ir.service.IRAdvicePrintS131Service;

/**
 * @author sinjen
 * S131-印製通知書
 */
@Slf4j
@RestController
@RequestMapping("/ir")
public class IRAdvicePrintS131Controller {
	
	@Autowired
	IRAdvicePrintS131Service irAdvicePrintS131Service;
	
	IRMaster irMaster = null;
	// ※※※ S131 API清單 ※※※
	// S131R 「處理種類」為(0、1、2、7或8) 之發查電文。 ==>進行通知書列印(多筆)
	@PutMapping("/s131r")
    @Operation(description = "進行通知書列印", summary="通知書列印")
    public List<IRMaster> qryAdvicePrint(String branch) {
		List<IRMaster> listData = new ArrayList<IRMaster>();
		listData = irAdvicePrintS131Service.qryAdvicePrint(branch);		
		
		if (listData != null)
		{
			log.info(branch + "分行通知書列印");			 
		}
		else
		{
			log.info("查無資料");
		}
		
		return listData;
    }	
	
	// S131I1 "「處理種類」為(3或4) 之發查電文。==>回傳「受通知筆數」、「已印製通知書筆數」欄位"
	@PutMapping("/s131i1")
    @Operation(description = "受通知筆數", summary="受通知筆數")
    public int[] qryAdviceCount(String branch) {
		int[] adviceCount = new int[2];
		adviceCount = irAdvicePrintS131Service.qryAdviceCount(branch);		
		
		//受通知筆數
		if (adviceCount[0] != 0)
		{
			log.info(branch + "分行受通知筆數" + adviceCount[0] + "筆");
			log.info(branch + "已印製通知書筆數" + adviceCount[1] + "筆");
		}
		else
		{
			log.info("查無資料");
		}
		
		return adviceCount;
    }	
	
	// S131I2 "「處理種類」為(5或6) 之發查電文。==>回傳S1311畫面"
	// S162I "「處理種類」為B之發查電文。==>回傳S1312畫面"
	// S162B S1312畫面上／下頁查詢。

}
