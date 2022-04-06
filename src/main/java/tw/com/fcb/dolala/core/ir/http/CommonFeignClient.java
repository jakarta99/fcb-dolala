package tw.com.fcb.dolala.core.ir.http;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tw.com.fcb.dolala.core.common.http.Response;
import tw.com.fcb.dolala.core.common.web.dto.BankAddressDto;
import tw.com.fcb.dolala.core.common.web.dto.BankDto;
import tw.com.fcb.dolala.core.common.web.dto.CustomerDto;

import java.math.BigDecimal;

@FeignClient(value = "CHECK",url = "localhost:8080")
public interface CommonFeignClient {
    // 匯率處理
    @GetMapping("/common/get-fxrate")
    @Operation(description = "依exchgRateType, currency, standardCurrency取得ExchgRate", summary = "讀取買/賣匯匯率")
    BigDecimal getFxRate(@RequestParam("fxRateType") String exchgRateType, @RequestParam("currency")String currency, @RequestParam("standardCurrency")String standardCurrency);

    @GetMapping("/common/check-fxrate")
    @Operation(description = "檢核承作匯率", summary = "檢核承作匯率")
    boolean CheckFxRate(@RequestParam("exchgRate") BigDecimal rate);

    // 國家資料處理
    @GetMapping("/common/{countryCode}/get-country-name")
    @Operation(description = "以國家代號英文2碼讀取國家代號4碼數字", summary = "讀取國家代號4碼數字")
    String GetCountryNumber(@RequestParam("countryCode") String  countryCode);

    @GetMapping("/common/{countryNumber}/get-country-code")
    @Operation(description = "以國家代號4碼數字讀取國家代號英文2碼", summary = "讀取國家代號英文2碼")
    String GetCountryCode(@RequestParam("countryNumber") String  countryNumber);

    // 身分證號檢核
    @GetMapping("/common/{id}/check-id")
    @Operation(description = "檢核居留證或統一證號是否符合編碼規則", summary = "身分證號檢核")
    boolean CheckId(@RequestParam("checkId") String  id);

     //讀取取號檔
    @GetMapping("/common/{systemType}/{branch}/get-number-serial")
    @Operation(description = "查詢取號檔資訊",summary = "BY業務別查詢取號檔已使用到之號碼")
    Long isGetNumberSerial(@RequestParam("systemType") String  systemType, @RequestParam("branch") String branch);

    // 取得外匯編號 FXNO
    @PutMapping("/common/{noCode}/{systemType}/{branch}/get-fx-no")
    @Operation(description = "取得外匯編號",summary = "取得外匯編號並更新取號檔")
    String getFxNo(@RequestParam("noCode")String nocode,@RequestParam("systemType")String systemType,@RequestParam("branch")String branch);

    // 取得IRCase seqNo
    @PutMapping("/common/get-ir-seq-no")
    @Operation(description = "取得匯入IRCase SEQ_NO",summary = "取得匯入IRCase SEQ_NO並更新取號檔")
    String getSeqNo();

    //顧客資料處理
    @GetMapping("/common/customeraccount/{accountNumber}")
    @Operation(description = "以顧客帳號讀取顧客資料", summary = "讀取顧客資料")
    Response<CustomerDto> getCustomer(@RequestParam("accountNumber")String accountNumber);
    
    // 分行資料處理
    @GetMapping("/common/branch")
    @Operation(description = "傳入分行號碼取得分行字軌",summary = "以分行代號取得分行字軌")
	String getBranchCode(@RequestParam("branch")String branch);

    // 讀銀行檔
    @GetMapping("/common/bank/{swiftCode}")
    @Operation(description = "傳入SwiftCode查詢銀行檔", summary="以SwiftCode查詢銀行檔")
    BankDto getBank(@RequestParam("swiftCode")String swiftCode);

    // TCTYR02 以匯款行/付款行國家代號查詢名稱
    @GetMapping("/common/bank/countryname/{countrycode}")
    @Operation(description = "傳入CountryCode查詢國家名稱", summary="以CountryCode查詢國家名稱")
    String getCountryName(@RequestParam("swiftCode") String countryCode);

    // TBNMR12 依劃帳行ID+幣別代碼 查詢劃帳行名稱地址
    @GetMapping("/common/bank/countryadd/{swiftcode}/{currency}")
    @Operation(description = "傳入劃帳行ID+幣別代碼查詢劃帳行名稱地址", summary="以劃帳行ID+幣別代碼查詢劃帳行名稱地址")
    BankAddressDto getBankAdd(@RequestParam("swiftCode") String swiftCode, @RequestParam("currency") String currency);

    // TBNMR13 依劃帳行ID 查詢劃帳行名稱地址 (幣別代碼=99)
    @GetMapping("/common/bank/countryadd/{swiftcode}/99")
    @Operation(description = "傳入劃帳行ID+99查詢劃帳行名稱地址", summary="以劃帳行ID+99查詢劃帳行名稱地址")
    Response<BankAddressDto> getBankAdd(@RequestParam("swiftCode") String swiftCode);

    // 手續費計算
    @GetMapping("/common/GetChargeFeeTWD")
    @Operation(description = "依currency, amount取得chargeFee(新台幣)", summary = "手續費計算")
    BigDecimal isGetChargeFeeTWD(@RequestParam("currency")String currency, @RequestParam("amount")String amount);

    // 讀取匯款性質名稱
    @GetMapping("/common/GetRemitNature")
	@Operation(description = "依remitNatureCode, remitNatureType取得remitNatureName", summary = "讀取匯款性質名稱")
    String isGetRemitNature(@RequestParam("remitNatureCode")String remitNatureCode, @RequestParam("remitNatureType")String remitNatureType);

    @GetMapping("/common/errorcode/{errorCode}")
    @Operation(description = "傳入errorCode查詢錯誤說明", summary="以errorCode查詢錯誤說明")
    String getErrorMessage(@RequestParam("errorCode")String errorCode);

}
