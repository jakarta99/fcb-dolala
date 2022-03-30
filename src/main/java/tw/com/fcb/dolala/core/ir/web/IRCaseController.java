package tw.com.fcb.dolala.core.ir.web;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tw.com.fcb.dolala.core.common.enums.ResponseStatus;
import tw.com.fcb.dolala.core.common.http.Response;
import tw.com.fcb.dolala.core.common.service.*;
import tw.com.fcb.dolala.core.common.web.dto.BankDto;
import tw.com.fcb.dolala.core.common.web.dto.Customer;
import tw.com.fcb.dolala.core.ir.http.CommonFeignClient;
import tw.com.fcb.dolala.core.ir.service.IRCaseService;
import tw.com.fcb.dolala.core.ir.service.IRMessageCheckSerivce;
import tw.com.fcb.dolala.core.ir.vo.IRCaseVo;
import tw.com.fcb.dolala.core.ir.web.cmd.SwiftMessageSaveCmd;
import tw.com.fcb.dolala.core.ir.web.dto.IRCase;

import java.math.BigDecimal;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: IRCaseController
 * Author: Han-Ru
 * Date: 2022/3/10 下午 02:51
 * Description: IRSwiftController
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@RestController
@RequestMapping("/ir")
public class IRCaseController {

    @Autowired
    IRCaseService irCaseService;
    @Autowired
    IRMessageCheckSerivce irMessageCheckSerivce;
    @Autowired
    ErrorMessageService errorMessageService;
    @Autowired
    CommonFeignClient feignClient;

    @PostMapping("/swift")
    @Operation(description = "接收 swift 電文並存到 SwiftMessage", summary="儲存 swift")
    public Response receiveSwift(@Validated @RequestBody SwiftMessageSaveCmd message) {
        //取號
        String irSeqNo = null;
        Response response = new Response<>();
        try {
            IRCaseVo irCaseVo = new IRCaseVo();
            BeanUtils.copyProperties(message, irCaseVo);
            // STATUS 七日檔初始狀態
            //      1 ：初值
            //      2 ：印製放行工作單訖(經辦放行) (S111交易)
            //          (受通知單位係其它外匯指定單位時放 2 ， ELSE 放 4 )
            irCaseVo.setProcessStatus("1");
            // check account
                String accountNo = irMessageCheckSerivce.getAccountNo(message.getReceiverAccount());
                irCaseVo.setReceiverAccount(accountNo);
            // check currency
                boolean checkOK;
                checkOK =   irMessageCheckSerivce.checkCurrency(message.getCurrency());
                if (checkOK == true)
                {
                    String currency = message.getCurrency();
                    irCaseVo.setCurrency(currency);
                }
            //讀取都市檔
            //讀取存匯行關係
            //讀取是否為同存行
            //讀取共用服務 set相關欄位
            irCaseService.saveIRCaseData(irCaseVo);
            //insert，將電文資料新增至IRCase檔案
            irCaseService.insert(irCaseVo);

            response.Success();
            response.setData(irCaseVo);

        } catch (Exception e) {
            response.Error(e.getMessage(),feignClient.getErrorMessage(e.getMessage()));
        }


        return response;
    }

    @GetMapping("/swift/{id}")
    @Operation(description = "檢核是否可自動放行", summary="更新AUTO_PASS欄位")
    public Boolean checkAutoPassMK(String  irSeqNo) {
        irCaseService.getByIRSeqNo(irSeqNo);
        // check 相關欄位

        return true;
    }

    @GetMapping("/swift")
    @Operation(description = "取得seqNo電文資料",summary="取得seqNo電文資料")
    public IRCase getBySeqNo(String irSeqNo){

        return irCaseService.getByIRSeqNo(irSeqNo);
    }
    public String getMessage(String errorCode) {
        Response response = new Response<>();
        String errorMessage = null;
        try {
            errorMessage = null;
            errorMessage = errorMessageService.findByErrorCode(errorCode);

        } catch (Exception e) {
            response.setStatus(ResponseStatus.ERROR);
            response.setCode(String.valueOf(e.getMessage()).substring(0, 4));
            response.setMessage(getMessage(e.getMessage()));
        }
        return errorMessage;
    }
}
