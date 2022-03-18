package tw.com.fcb.dolala.core.ir.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sinjen
 * S211-原幣匯入匯款解款
 */
@Slf4j
@RestController
@RequestMapping("/ir")
public class IROriginalCcyReleaseS211Controller {

	// ※※※ S211 API清單 ※※※
	// S211A 執行原幣解款資料新增 (A:新增)
	// S211C 執行原幣解款資料更正 (C:更正)
	// S211D 執行原幣解款資料剔除 (D:剔除)
	// S211I 依匯入編號資料查詢解款資料 (A,C,D)
	// S211P 於交易完成後，端末判斷須列印申報書者，提示訊息，若選擇列印，則發S211P取得申報書資料列印
	// S211U 將前端 AML 取號相關資料回寫主檔，並檢核疑似第三方交易、客戶是否曾被婉拒交易
	// NOTCR06 依通報序號查詢通報匯率及通報匯率成本
	// S168R01 單日結匯金額檢查(即期結匯!=0且送件編號!=空白)
	// S168R03 單日結匯金額檢查(即期結匯!=0且送件編號=空白)
	// S168R04 單日結匯金額檢查(匯款幣別=25)
	// SACCR01 查詢帳號-東亞證券複委託客戶
	// SACCR02 台幣扣款帳號&取款碼檢核
	// SACCR03 查詢(或驗證)台幣委託代繳扣款帳號
	// SCOMI 查詢手續費
	// SIDCR01 身分別為５ (檢查統編是否為總行或分行統編)
	// SIDCR02 查詢居留證核發日/居留證有效日
	// TAOCR09 查詢與檢核實際招攬人員、轉介/協銷人員
	// TCTYR02 查詢匯出國別名稱
	// TCTYR05 查詢國籍名稱
	// TCUSR08 讀取顧客名稱 (依客戶編號查詢客戶名稱)
	// TFFVR08 查詢遠期結匯匯率、結匯AMT
	// TFXRR27 依承作日&幣別查詢即期/現鈔匯率資料

}
