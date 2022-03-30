package tw.com.fcb.dolala.core.ir.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sinjen
 * S061-作業部退匯(無匯入編號)
 */
@Slf4j
@RestController
@RequestMapping("/ir")
public class ReturnIRCaseS061Controller {

	// ※※※ S061 API清單 ※※※
	// S061A	退匯(無匯入編號) (A)
	// S061C	退匯(無匯入編號) (C)
	// S061D	退匯(無匯入編號) (D)
	// S061P	退匯(無匯入編號) (P)
	// S061I	退匯(無匯入編號) (A/C/D/P) 前資料查詢
	// TFXRR27	查詢即期結匯匯率
	// TBNMR12	查詢轉匯存匯行名稱地址。(幣別非99-台幣)
	// TBNMR13	查詢轉匯存匯行名稱地址。(幣別99-台幣)

}
