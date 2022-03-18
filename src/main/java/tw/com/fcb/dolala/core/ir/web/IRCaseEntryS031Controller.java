package tw.com.fcb.dolala.core.ir.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sinjen
 * S031-匯款資料輸入
 */
@Slf4j
@RestController
@RequestMapping("/ir")
public class IRCaseEntryS031Controller {

	// ※※※ S031 API清單 ※※※
	// S031A 執行匯款資料新增 (A:新增/T:複製電文)
	// S031C 執行匯款資料更正 (C:更正)
	// S031D 執行匯款資料剔除 (D:剔除)
	// S031I 執行匯款資料查詢 (C:更正/ D:剔除/T:複製電文)前資料查詢
	// TCTYR02 以匯款行/付款行國家代號查詢名稱
	// TBNMR09 以SWIFT-TID CODE查詢
	// TBNMR12 依劃帳行ID+幣別代碼 查詢劃帳行名稱地址
	// TBNMR13 依劃帳行ID 查詢劃帳行名稱地址 (幣別代碼=99)

}
