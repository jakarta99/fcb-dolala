package tw.com.fcb.dolala.core.common.web;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import tw.com.fcb.dolala.core.common.repository.entity.SerialNumber;
import tw.com.fcb.dolala.core.common.service.CountryService;
import tw.com.fcb.dolala.core.common.service.ExchgRateService;
import tw.com.fcb.dolala.core.common.service.IDNumberCheckService;
import tw.com.fcb.dolala.core.common.service.SerialNumberService;

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
	@Autowired
	CountryService countryService;
	@Autowired
	IDNumberCheckService idNumberCheckService;
	@Autowired
	SerialNumberService serialNumberService;

	// 匯率處理
	@GetMapping("/fxrate")
	@Operation(description = "依exchgRateType, currency, standardCurrency取得ExchgRate", summary = "讀取買/賣匯匯率")
	public BigDecimal getFxRate(String exchgRateType, String currency, String standardCurrency) {
		BigDecimal exchangeRate = fxService.getRate(exchgRateType, currency, standardCurrency);
		log.info("呼叫讀取匯率API：取得ExchgRate = " + exchangeRate);
		return exchangeRate;
	}

	@GetMapping("/checkfxrate")
	@Operation(description = "檢核承作匯率", summary = "檢核承作匯率")
	public boolean checkFxRate(BigDecimal exchgRate) {
		log.info("呼叫匯率處理API：檢核承作匯率是否超過權限範圍");
		log.info("呼叫匯率處理API：檢核承作匯率是否超出合理範圍");
		return true;
	}

	// 國家資料處理
	@GetMapping("/countrynumber")
	@Operation(description = "以國家代號英文2碼讀取國家代號4碼數字", summary = "讀取國家代號4碼數字")
	public String getCountryNumber(String countryCode) {
		String countryNumber = null;
		countryNumber = countryService.getCountryNumber(countryCode);
		log.info("呼叫國別處理API：以國家代號2碼:" + countryCode + " 讀取國家代號4碼:" + countryNumber);
		return countryNumber;
	}

	@GetMapping("/countrycode")
	@Operation(description = "以國家代號4碼數字讀取國家代號英文2碼", summary = "讀取國家代號英文2碼")
	public String getCountryCode(String countryNumber) {
		String countryCode = null;
		countryCode = countryService.getCountryCode(countryNumber);
		log.info("呼叫國別處理API：以國家代號4碼:" + countryNumber + " 讀取國家代號2碼:" + countryCode);
		return countryCode;
	}

	// 身分證號檢核
	@GetMapping("/checkid")
	@Operation(description = "檢核居留證或統一證號是否符合編碼規則", summary = "身分證號檢核")
	public boolean checkId(String number) {
		boolean check = false;
		check = idNumberCheckService.isValidIDorRCNumber(number);
		log.info("呼叫身分證號檢核API：檢核" + number + "是否符合編碼規則:" + check);
		return check;
	}
	//讀取取號檔
	@GetMapping("/numberSerial")
	@Operation(description = "查詢取號檔資訊",summary = "BY業務別查詢取號檔已使用到之號碼")
	public Long getNumberSerial(String systemType, String branch){
		SerialNumber serialNumber = serialNumberService.getNumberSerial(systemType,branch);
		log.info("呼叫讀取取號檔API查詢"+ systemType + "現已使用到第"+ serialNumber.getSerialNo()+ "號");
		return serialNumber.getSerialNo();
	}


	//顧客資料處理
	
	
	//銀行資料處理
	
	
	//分行資料處理
	
	
}
