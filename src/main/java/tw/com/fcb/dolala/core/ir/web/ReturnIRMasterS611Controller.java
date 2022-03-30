package tw.com.fcb.dolala.core.ir.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sinjen
 * S611-分行退匯
 */
@Slf4j
@RestController
@RequestMapping("/ir")
public class ReturnIRMasterS611Controller {

	// ※※※ S611 API清單 ※※※
	// S611I	查詢匯入退匯資料
	// S611A	新增退匯交易
	// S611C	更正退匯交易
	// S611D	剔除退匯交易
	// NOTCR07	依通報序號查詢通報匯率及通報匯率成本
	// TCTYR05	讀取都市國別檔
	// SIDCR01	身分別為５ (檢查統編是否為總行或分行統編)
	// TCUSR08	依客戶編號查詢客戶名稱(MIXED)
	// TFXRR29	讀取即期賣匯匯率及賣匯匯率成本資料

}
