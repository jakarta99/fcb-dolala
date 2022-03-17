package tw.com.fcb.dolala.core.ir.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sinjen
 * S131-印製通知書
 */
@Slf4j
@RestController
@RequestMapping("/ir")
public class IRAdvicePrintS131Controller {

	// ※※※ S131 API清單 ※※※
	// S131R 「處理種類」為(0、1、2、7或8) 之發查電文。 ==>進行通知書列印
	// S131I1 "「處理種類」為(3或4) 之發查電文。==>回傳「受通知筆數」、「已印製通知書筆數」欄位"
	// S131I2 "「處理種類」為(5或6) 之發查電文。==>回傳S1311畫面"
	// S162I "「處理種類」為B之發查電文。==>回傳S1312畫面"
	// S162B S1312畫面上／下頁查詢。

}
