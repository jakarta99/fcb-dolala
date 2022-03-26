package tw.com.fcb.dolala.core.ir.http;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tw.com.fcb.dolala.core.common.enums.ResponseStatus;
import tw.com.fcb.dolala.core.common.http.Response;
import tw.com.fcb.dolala.core.common.repository.entity.SerialNumber;
import tw.com.fcb.dolala.core.common.web.dto.BankAddressDto;
import tw.com.fcb.dolala.core.common.web.dto.BankDto;
import tw.com.fcb.dolala.core.common.web.dto.Customer;
import tw.com.fcb.dolala.core.common.web.dto.CustomerAccount;
import tw.com.fcb.dolala.core.common.web.vo.BankVo;

import java.math.BigDecimal;

@FeignClient(value = "CHECK",url = "localhost:8080")
public interface IRFieignClient {
    // 匯率處理
    @GetMapping("/common/GetFxRate")
    @Operation(description = "依exchgRateType, currency, standardCurrency取得ExchgRate", summary = "讀取買/賣匯匯率")
    BigDecimal isGetFxRate(@RequestParam("fxRateType") String exchgRateType,@RequestParam("currency")String currency,@RequestParam("standardCurrency")String standardCurrency);


//
//    @GetMapping("/CheckFxrate")
//    @Operation(description = "檢核承作匯率", summary = "檢核承作匯率")
//    boolean isCheckFxRate(@RequestParam("checkfxrate") BigDecimal rate);
//
//    // 國家資料處理
//    @GetMapping("/CountryNumber")
//    @Operation(description = "以國家代號英文2碼讀取國家代號4碼數字", summary = "讀取國家代號4碼數字")
//    String isGetCountryNumber(@RequestParam("countryNumber") String  countryCode);
//
//
//    @GetMapping("/CountryCode")
//    @Operation(description = "以國家代號4碼數字讀取國家代號英文2碼", summary = "讀取國家代號英文2碼")
//    String isGetCountryCode(@RequestParam("countrycode") String  countryNumber);
//
//
//    // 身分證號檢核
//    @GetMapping("/CheckId")
//    @Operation(description = "檢核居留證或統一證號是否符合編碼規則", summary = "身分證號檢核")
//    boolean isCheckId(@RequestParam("checkid") String  number);
//
//    // 讀取取號檔
//    @GetMapping("/NumberSerial")
//    @Operation(description = "查詢取號檔資訊",summary = "BY業務別查詢取號檔已使用到之號碼")
//    Long isGetNumberSerial(@RequestParam("numberSerial") String  systemType, String branch);

    // 取得外匯編號 FXNO
    @PutMapping("/common/FxNo")
    @Operation(description = "取得外匯編號",summary = "取得外匯編號並更新取號檔")
    String isGetFxNo(@RequestParam("FxNo")String branch);
//
    // 取得IRCase seqNo
    @PutMapping("/common/SeqNo")
    @Operation(description = "取得匯入IRCase SEQ_NO",summary = "取得匯入IRCase SEQ_NO並更新取號檔")
    String isGetSeqNo(@RequestParam("branch")String branch);

////
//    //顧客資料處理
//    @GetMapping("/customer")
//    @Operation(description = "以顧客帳號讀取顧客資料", summary = "讀取顧客資料")
//    public Customer getCustomer(String accountNumber) {
//        CustomerAccount customerAccount = null;
//        customerAccount = customerAccountService.getCustomerAccount(accountNumber);
//        log.info("呼叫讀取顧客帳戶API：顧客帳戶資料："+customerAccount.toString());
//
//        Customer customer= null;
//        customer = customerService.getCustomer(customerAccount.getCustomerSeqNo());
//        log.info("呼叫讀取顧客檔API：顧客資料："+customer.toString());
//        return customer;
//    }
//
//    // 分行資料處理
//    @GetMapping("/branch")
//    @Operation(description = "傳入分行號碼取得分行字軌",summary = "以分行代號取得分行字軌")
//    public String getBranchCode(String branch){
//        String branchCode = null;
//        try {
//            branchCode = branchCheckService.getBranchCode(branch);
//            log.info("呼叫取得分行字軌API：branch = " + branch + "字軌 = " + branchCode);
//        } catch (Exception e) {
//            log.info("讀取branchService錯誤" + e);
//
//        }
//        return branchCode;
//    }
//
//    // 讀銀行檔
//    @GetMapping("/bank/{swiftcode}")
//    @Operation(description = "傳入SwiftCode查詢銀行檔", summary="以SwiftCode查詢銀行檔")
//    public BankDto getBank(String swiftCode) {
//        BankDto bankDto = new BankDto();
//        BankVo bankVo = new BankVo();
//        try {
//            bankVo = bankService.findBySwiftCode(swiftCode);
//            BeanUtils.copyProperties(bankVo, bankDto);
//            log.info("呼叫取得銀行檔API：取得swiftCode = " + swiftCode + "之銀行檔");
//        }catch(Exception e) {
//            log.info(String.valueOf(e));
//        }
//
//        return bankDto;
//    }
//
//    // TCTYR02 以匯款行/付款行國家代號查詢名稱
//    @GetMapping("/bank/countryname/{countrycode}")
//    @Operation(description = "傳入CountryCode查詢國家名稱", summary="以CountryCode查詢國家名稱")
//    public String getCountryName(String countryCode) {
//
//        log.info("呼叫讀取國家名稱API查詢 "+countryCode);
//
//        return "CountryName";
//    }
//
//    // TBNMR12 依劃帳行ID+幣別代碼 查詢劃帳行名稱地址
//    @GetMapping("/bank/countryadd/{swiftcode}/{currency}")
//    @Operation(description = "傳入劃帳行ID+幣別代碼查詢劃帳行名稱地址", summary="以劃帳行ID+幣別代碼查詢劃帳行名稱地址")
//    public BankAddressDto getBankAdd(String swiftCode, String currency) {
//        BankAddressDto bankAddressDto = new BankAddressDto();
//
//        log.info("呼叫劃帳行名稱地址API查詢 "+swiftCode+"+"+currency);
//
//        bankAddressDto.setName("ABank");
//        bankAddressDto.setAddress("No.1, X st.,Tapei City 106");
//        return bankAddressDto;
//    }
//
//    // TBNMR13 依劃帳行ID 查詢劃帳行名稱地址 (幣別代碼=99)
//    @GetMapping("/bank/countryadd/{swiftcode}/99")
//    @Operation(description = "傳入劃帳行ID+99查詢劃帳行名稱地址", summary="以劃帳行ID+99查詢劃帳行名稱地址")
//    public Response<BankAddressDto> getBankAdd(String swiftCode) {
//        BankAddressDto bankAddressDto = new BankAddressDto();
//        BankVo bankVo = new BankVo();
//        Response<BankAddressDto> response = new Response<BankAddressDto>();
//
//        try {
//            bankVo = bankService.findBySwiftCode(swiftCode);
//            BeanUtils.copyProperties(bankVo, bankAddressDto);
//            log.info("呼叫劃帳行名稱地址API查詢 "+swiftCode+"+"+99);
//            response.setStatus(ResponseStatus.SUCCESS);
//            response.setCode("0000");
//        }catch(Exception e) {
//            log.info(String.valueOf(e));
//            response.setStatus(ResponseStatus.ERROR);
//            response.setCode("D001");
//            response.setMessage(String.valueOf(e));
//        }
//        response.setData(bankAddressDto);
//        return response;
//    }
//
//

}
