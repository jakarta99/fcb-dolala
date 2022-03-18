package tw.com.fcb.dolala.core.common.web;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import tw.com.fcb.dolala.core.common.service.ExchgRateService;

/**
 * @author sinjen
 * 
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

	@Autowired
	ExchgRateService fxService;
	
	//匯率處理
	@GetMapping("/fxrate")
	@Operation(description = "依exchgRateType, currency, standardCurrency取得ExchgRate",summary="讀取買/賣匯匯率")
	public BigDecimal getFxRate(String exchgRateType, String currency, String standardCurrency) {
		BigDecimal exchangeRate = fxService.getRate(exchgRateType, currency, standardCurrency);
		log.info("呼叫讀取匯率API：取得ExchgRate = "+exchangeRate);
		return exchangeRate;
	}
	
	@GetMapping("/checkfxrate")
	@Operation(description = "檢核承作匯率",summary="檢核承作匯率")
	public boolean checkFxRate(String exchgRateType) {
		log.info("呼叫匯率處理API：檢核承作匯率是否超過權限範圍");
		log.info("呼叫匯率處理API：檢核承作匯率是否超出合理範圍");
		return true;
	}
	
	
	//顧客資料處理
	
	
	//銀行資料處理
	
	
	//分行資料處理
	
	
	//國家資料處理
	
	
}
