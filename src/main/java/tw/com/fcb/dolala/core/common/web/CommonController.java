package tw.com.fcb.dolala.core.common.web;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import tw.com.fcb.dolala.core.common.repository.entity.SerialNumber;
import tw.com.fcb.dolala.core.common.service.*;
import tw.com.fcb.dolala.core.common.web.dto.BankDto;
import tw.com.fcb.dolala.core.common.service.CountryService;
import tw.com.fcb.dolala.core.common.service.CustomerAccountService;
import tw.com.fcb.dolala.core.common.service.CustomerService;
import tw.com.fcb.dolala.core.common.service.ExchgRateService;
import tw.com.fcb.dolala.core.common.service.IDNumberCheckService;
import tw.com.fcb.dolala.core.common.web.dto.Customer;
import tw.com.fcb.dolala.core.common.web.dto.CustomerAccount;

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
	@Autowired
	BankService bankService;
	@Autowired
	CustomerAccountService customerAccountService;
	@Autowired
	CustomerService customerService;
	@Autowired
	BranchCheckService branchCheckService;

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
	//取得外匯編號 FXNO
	@PutMapping("/numberSerial")
	@Operation(description = "取得外匯編號",summary = "取得外匯編號並更新取號檔")
	public String getFxNo(String noCode,String systemType,String branch)  {
		String fxNo = null;
		try {
			 fxNo =  serialNumberService.getFxNo(noCode,systemType,branch);
			String numberSerial = null;
			if (branch.equals("093")){
				numberSerial = fxNo.substring(5, 11);
				log.info("取得serialno = " + fxNo.substring(6,11));
			}else {
				numberSerial = fxNo.substring(5, 10);
			}
			serialNumberService.updateSerialNumber(systemType,branch, Long.valueOf(numberSerial));
			log.info("呼叫取得外匯編號API,FXNO = "+fxNo+ ", 並更新取號檔成功 = " + numberSerial );

		} catch (Exception e) {
			log.info("取得外匯編號錯誤" + e);

		}
		return  fxNo;
	}
	//取得IRCase seqNo
	@PutMapping("/")
	@Operation(description = "取得匯入IRCase SEQ_NO",summary = "取得匯入IRCase SEQ_NO並更新取號檔")
	public String getSeqNo()  {
		String irSeq = null;
		final String branch = "999";
		final String systemType = "IR_SEQ";
		try {
			irSeq =  serialNumberService.getIrSeqNo(systemType,branch);

			serialNumberService.updateSerialNumber(systemType,branch, Long.valueOf(irSeq));
			log.info("呼叫取得IRCase SEQ_NO API,SEQ_NO = "+irSeq+ ", 並更新取號檔成功"  );

		} catch (Exception e) {
			log.info("取得SEQ_NO錯誤" + e);

		}
		return  irSeq;
	}


	//顧客資料處理
	@GetMapping("/customer")
	@Operation(description = "以顧客帳號讀取顧客資料", summary = "讀取顧客資料")
	public Customer getCustomer(String accountNumber) {
		CustomerAccount customerAccount = null;
		customerAccount = customerAccountService.getCustomerAccount(accountNumber);
		log.info("顧客帳戶資料："+customerAccount.toString());
		
		Customer customer= null;
		customer = customerService.getCustomer(customerAccount.getCustomerSeqNo());
		log.info("顧客資料："+customer.toString());
		return customer;
	}
	
	//銀行資料處理
	
	
	//分行資料處理
	@GetMapping("/branch")
	@Operation(description = "傳入分行號碼取得分行字軌",summary = "以分行代號取得分行字軌")
	public String getBranchCode(String branch){
		String branchCode = null;
		try {
			branchCode = branchCheckService.getBranchCode(branch);
			log.info("呼叫取得分行字軌API branch = " + branch + "字軌 = " + branchCode);
		} catch (Exception e) {
			log.info("讀取branchService錯誤" + e);

		}
		return branchCode;
	}


	//讀銀行檔
	@GetMapping("/bank/{swiftcode}")
	@Operation(description = "傳入SwiftCode查詢銀行檔", summary="以SwiftCode查詢銀行檔")
	public BankDto getBank(String swiftCode) {
		BankDto bankDto = new BankDto();
		try {
			bankDto = bankService.findBySwiftCode(swiftCode);
			log.info("取得銀行檔 "+swiftCode);
		}catch(Exception e) {
			log.info(String.valueOf(e));
		}

		return bankDto;
	}



}
