package tw.com.fcb.dolala.core.common.web;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import tw.com.fcb.dolala.core.common.enums.ResponseStatus;
import tw.com.fcb.dolala.core.common.http.Response;
import tw.com.fcb.dolala.core.common.repository.entity.SerialNumber;
import tw.com.fcb.dolala.core.common.service.*;
import tw.com.fcb.dolala.core.common.web.dto.BankAddressDto;
import tw.com.fcb.dolala.core.common.web.dto.BankDto;
import tw.com.fcb.dolala.core.common.service.CountryService;
import tw.com.fcb.dolala.core.common.service.CustomerAccountService;
import tw.com.fcb.dolala.core.common.service.CustomerService;
import tw.com.fcb.dolala.core.common.service.ExchgRateService;
import tw.com.fcb.dolala.core.common.service.IDNumberCheckService;
import tw.com.fcb.dolala.core.common.web.dto.CustomerDto;
import tw.com.fcb.dolala.core.common.web.dto.CustomerAccountDto;
import tw.com.fcb.dolala.core.common.web.vo.BankVo;
import tw.com.fcb.dolala.core.config.IRConfig;

/**
 * @author sinjen
 * 
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

	@Autowired
	IRConfig irConfig;

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
	@Autowired
	ErrorMessageService errorMessageService;
	@Autowired
	ChargeFeeCalculateService chargeFeeCalculateService;
	@Autowired
	RemitNatureService remitNatureService;


	// 匯率處理
	@GetMapping("/get-fxrate/{exchg-rate-type}/{currency}/{standard-currency}")
	@Operation(description = "依exchgRateType, currency, standardCurrency取得ExchgRate", summary = "讀取買/賣匯匯率")
	public BigDecimal isGetFxRate(@PathVariable("exchg-rate-type") String exchgRateType, @PathVariable("currency") String currency, @PathVariable("standard-currency") String standardCurrency) {
		log.info("${env-type} = {}",irConfig.getEnvType());
		BigDecimal exchangeRate = fxService.getRate(exchgRateType, currency, standardCurrency);
		log.info("呼叫讀取匯率API：取得ExchgRate = " + exchangeRate);
		return exchangeRate;
	}

	@GetMapping("/check-fxrate")
	@Operation(description = "檢核承作匯率", summary = "檢核承作匯率")
	public boolean isCheckFxRate(BigDecimal exchgRate) {
		log.info("呼叫匯率處理API：檢核承作匯率是否超過權限範圍");
		log.info("呼叫匯率處理API：檢核承作匯率是否超出合理範圍");
		return true;
	}

	// 國家資料處理
	@GetMapping("/{countryCode}/get-country-name")
	@Operation(description = "以國家代號英文2碼讀取國家代號4碼數字", summary = "讀取國家代號4碼數字")
	public String isGetCountryNumber(String countryCode) {
		String countryNumber = null;
		countryNumber = countryService.getCountryNumber(countryCode);
		log.info("呼叫國別處理API：以國家代號2碼:" + countryCode + " 讀取國家代號4碼:" + countryNumber);
		return countryNumber;
	}

	@GetMapping("/{countryNumber}/get-country-code")
	@Operation(description = "以國家代號4碼數字讀取國家代號英文2碼", summary = "讀取國家代號英文2碼")
	public String isGetCountryCode(String countryNumber) {
		String countryCode = null;
		countryCode = countryService.getCountryCode(countryNumber);
		log.info("呼叫國別處理API：以國家代號4碼:" + countryNumber + " 讀取國家代號2碼:" + countryCode);
		return countryCode;
	}

	// 身分證號檢核
	@GetMapping("/{id}/check-id")
	@Operation(description = "檢核居留證或統一證號是否符合編碼規則", summary = "身分證號檢核")
	public boolean isCheckId(String id) {
		boolean check = false;
		check = idNumberCheckService.isValidIDorRCNumber(id);
		log.info("呼叫身分證號檢核API：檢核" + id + "是否符合編碼規則:" + check);
		return check;
	}
	
	// 讀取取號檔
	@GetMapping("/{systemType}/{branch}/get-number-serial")
	@Operation(description = "查詢取號檔資訊",summary = "BY業務別查詢取號檔已使用到之號碼")
	public Long isGetNumberSerial(@PathVariable String systemType, @PathVariable String branch){
		SerialNumber serialNumber = serialNumberService.getNumberSerial(systemType,branch);
		log.info("呼叫讀取取號檔API：查詢"+ systemType + "現已使用到第"+ serialNumber.getSerialNo()+ "號");
		return serialNumber.getSerialNo();
	}

	// 取得外匯編號 FXNO
	@PutMapping("/{noCode}/{systemType}/{branch}/get-fx-no")
	@Operation(description = "取得外匯編號",summary = "取得外匯編號並更新取號檔")
	public String getFxNo(@PathVariable String noCode, @PathVariable String systemType, @PathVariable String branch)  {
		String fxNo = null;
		try {
			 fxNo =  serialNumberService.getFxNo(systemType,branch);
			String numberSerial = null;
			if (branch.equals("093")){
				numberSerial = fxNo.substring(6, 10);
				log.info("呼叫取得外匯編號API：取得serialno = " + fxNo.substring(6,10));
			}else {
				numberSerial = fxNo.substring(5, 10);
			}
			serialNumberService.updateSerialNumber(systemType,branch, Long.valueOf(numberSerial));
			log.info("呼叫取得外匯編號API：FXNO = "+fxNo+ ", 並更新取號檔成功 = " + numberSerial );

		} catch (Exception e) {
			log.info("取得外匯編號錯誤" + e);
		}
		return  fxNo;
	}

	// 取得IRCase seqNo
	@PutMapping("/get-ir-seq-no")
	@Operation(description = "取得匯入IRCase SEQ_NO",summary = "取得匯入IRCase SEQ_NO並更新取號檔")
	public String getSeqNo()  {
		String irSeq = null;

		final String branch = "999";

		final String systemType = "IR_SEQ";
		try {
			irSeq =  serialNumberService.getIrSeqNo(systemType,branch);

			serialNumberService.updateSerialNumber(systemType,branch, Long.valueOf(irSeq));
			log.info("呼叫取得IRCase SEQ_NO API：SEQ_NO = "+irSeq+ ", 並更新取號檔成功");

		} catch (Exception e) {
			log.info("取得SEQ_NO錯誤" + e);
		}
		return  irSeq;
	}


	//顧客資料處理
	@GetMapping("/customer-account/{accountNumber}")
	@Operation(description = "以顧客帳號讀取顧客資料", summary = "依帳號讀取顧客資料")
	public Response<CustomerDto> getCustomer(@PathVariable String accountNumber) {
		log.info("接收accountNumber = " + accountNumber);
		CustomerDto customerDto= null;
		Response<CustomerDto> response = new Response<CustomerDto>();
		CustomerAccountDto customerAccountDto = null;
		try {
			customerAccountDto = customerAccountService.getCustomerAccount(accountNumber);
			log.info("呼叫讀取顧客帳戶API：顧客帳戶資料："+customerAccountDto.toString());
			
			customerDto = customerService.getCustomer(customerAccountDto.getCustomerSeqNo());
			log.info("呼叫讀取顧客檔API：顧客資料："+customerDto.toString());
			response.Success();
		}catch(Exception e) {
			log.info(String.valueOf(e));
		}
		response.setData(customerDto);
		return response;

	}
	
	@GetMapping("/customerid/{customerId}")
	@Operation(description = "以顧客ID讀取顧客資料", summary = "依ID讀取顧客資料")
	public Response<CustomerDto> getCustomerId(@PathVariable String customerId) {
		log.info("接收accountId = " + customerId);
		CustomerDto customerDto= null;
		Response<CustomerDto> response = new Response<CustomerDto>();
		
		try {
			customerDto = customerService.getCustomerId(customerId);
			log.info("呼叫讀取顧客檔API：顧客資料："+customerDto.toString());
			response.Success();
		}catch(Exception e) {
			log.info(String.valueOf(e));
			response.Error(e.getMessage(),getErrorMessage(e.getMessage()));
		}
		response.setData(customerDto);
		return response;
	}
	
	// 分行資料處理
	@GetMapping("/{branch}/get-branch-code")
	@Operation(description = "傳入分行號碼取得分行字軌",summary = "以分行代號取得分行字軌")
	public String getBranchCode(@PathVariable String branch){
		String branchCode = null;
		try {
			branchCode = branchCheckService.getBranchCode(branch);
			log.info("呼叫取得分行字軌API：branch = " + branch + "字軌 = " + branchCode);
		} catch (Exception e) {
			log.info("讀取branchService錯誤" + e);
		}
		return branchCode;
	}

	// 讀銀行檔
	@GetMapping("/bank/{swiftCode}")
	@Operation(description = "傳入SwiftCode查詢銀行檔", summary="以SwiftCode查詢銀行檔")
	public BankDto getBank(@PathVariable String swiftCode) {
		BankDto bankDto = new BankDto();
		BankVo bankVo = new BankVo();
		try {
			bankVo = bankService.findBySwiftCode(swiftCode);
			BeanUtils.copyProperties(bankVo, bankDto);
			log.info("呼叫取得銀行檔API：取得swiftCode = " + swiftCode + "之銀行檔");
		}catch(Exception e) {
			log.info(String.valueOf(e));
		}
		return bankDto;
	}

	// TCTYR02 以匯款行/付款行國家代號查詢名稱
	@GetMapping("/bank/countryname/{countrycode}")
	@Operation(description = "傳入CountryCode查詢國家名稱", summary="以CountryCode查詢國家名稱")
	public String getCountryName(@PathVariable String countryCode) {

		log.info("呼叫讀取國家名稱API：查詢 "+countryCode);

		return "CountryName";
	}

	// TBNMR12 依劃帳行ID+幣別代碼 查詢劃帳行名稱地址
	@GetMapping("/bank/countryadd/{swiftcode}/{currency}")
	@Operation(description = "傳入劃帳行ID+幣別代碼查詢劃帳行名稱地址", summary="以劃帳行ID+幣別代碼查詢劃帳行名稱地址")
	public BankAddressDto getBankAdd(String swiftCode,String currency) {
		BankAddressDto bankAddressDto = new BankAddressDto();

		log.info("呼叫劃帳行名稱地址API：查詢 "+swiftCode+"+"+currency);

		bankAddressDto.setName("ABank");
		bankAddressDto.setAddress("No.1, X st.,Tapei City 106");
		return bankAddressDto;
	}

	// TBNMR13 依劃帳行ID 查詢劃帳行名稱地址 (幣別代碼=99)
	@GetMapping("/bank/countryadd/{swiftcode}/99")
	@Operation(description = "傳入劃帳行ID+99查詢劃帳行名稱地址", summary="以劃帳行ID+99查詢劃帳行名稱地址")
	public Response<BankAddressDto> getBankAdd(String swiftCode) {
		BankAddressDto bankAddressDto = new BankAddressDto();
		BankVo bankVo = new BankVo();
		Response<BankAddressDto> response = new Response<BankAddressDto>();

		try {
			bankVo = bankService.findBySwiftCode(swiftCode);
			BeanUtils.copyProperties(bankVo, bankAddressDto);
			log.info("呼叫劃帳行名稱地址API：查詢 "+swiftCode+"+"+99);
			response.Success();
		}catch(Exception e) {
			log.info(String.valueOf(e));
			response.Error(e.getMessage(),getErrorMessage(e.getMessage()));
		}
		response.setData(bankAddressDto);
		return response;
	}
	
	// 查詢error code
	@GetMapping("/errorcode/{errorcode}")
	@Operation(description = "傳入errorCode查詢錯誤說明", summary="以errorCode查詢錯誤說明")
	public String getErrorMessage(@PathVariable("errorcode") String errorCode) {
		String errorMessage = null;
		try {
			errorMessage = errorMessageService.findByErrorCode(errorCode);
		}catch (Exception e){
			errorMessage = "查無錯誤訊息";
		}

		return errorMessage;
	}

	// 手續費計算
	@GetMapping("/GetChargeFeeTWD")
	@Operation(description = "依currency, amount取得chargeFee(新台幣)", summary = "手續費計算")
	public BigDecimal isGetChargeFeeTWD(String currency, BigDecimal amount) {
		BigDecimal chargeFee = null;
		chargeFee = chargeFeeCalculateService.chargeFeeTWDCalculat(currency, amount);
		log.info("呼叫手續費計算API：輸入幣別" + currency + " 金額" + amount + " 取得新台幣手續費=" + chargeFee);
		return chargeFee;
	}
	
	// 讀取匯款性質名稱
	@GetMapping("/GetRemitNature")
	@Operation(description = "依remitNatureCode, remitNatureType取得remitNatureName", summary = "讀取匯款性質名稱")
	public String isGetRemitNature(String remitNatureCode, String remitNatureType) {
		String remitNatureName = null;
		remitNatureName = remitNatureService.getRemitNature(remitNatureCode, remitNatureType);
		log.info("呼叫匯款性質代碼API：輸入申報性質代碼" + remitNatureCode + " 匯款性質分類" + remitNatureType + " 取得申報性質名稱=" + remitNatureName);
		return remitNatureName;
	}
	
}
