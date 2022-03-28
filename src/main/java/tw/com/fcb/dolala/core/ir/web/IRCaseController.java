package tw.com.fcb.dolala.core.ir.web;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.fcb.dolala.core.common.enums.ResponseStatus;
import tw.com.fcb.dolala.core.common.http.Response;
import tw.com.fcb.dolala.core.common.service.*;
import tw.com.fcb.dolala.core.common.web.dto.BankDto;
import tw.com.fcb.dolala.core.common.web.dto.Customer;
import tw.com.fcb.dolala.core.ir.http.IRFieignClient;
import tw.com.fcb.dolala.core.ir.service.IRCaseService;
import tw.com.fcb.dolala.core.ir.service.IRMessageCheckSerivce;
import tw.com.fcb.dolala.core.ir.vo.IRCaseVo;
import tw.com.fcb.dolala.core.ir.web.cmd.SwiftMessageSaveCmd;
import tw.com.fcb.dolala.core.ir.web.dto.IRCase;

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
    SerialNumberService serialNumberService;
    @Autowired
    CustomerAccountService customerAccountService;
    @Autowired
    CustomerService customerService;
    @Autowired
    IRMessageCheckSerivce irMessageCheckSerivce;
    @Autowired
    ExchgRateService exchgRateService;
    @Autowired
    ErrorMessageService errorMessageService;
    @Autowired
    IRFieignClient irFieignClient;

    //取號檔 SystemType,branch
    private final String systemType = "IR_SEQ";
    private final String branch = "999";

    @PostMapping("/swift")
    @Operation(description = "接收 swift 電文並存到 SwiftMessage", summary="儲存 swift")
    public Response receiveSwift(SwiftMessageSaveCmd message) {
        //取號
        String irSeqNo = null;
        Response response = new Response<>();
        try {
            IRCaseVo irCaseVo = new IRCaseVo();
            // STATUS 七日檔初始狀態
            //      1 ：初值
            //      2 ：印製放行工作單訖(經辦放行) (S111交易)
            //          (受通知單位係其它外匯指定單位時放 2 ， ELSE 放 4 )
            irCaseVo.setProcessStatus("1");
            //欄位check
            // check account
            irCaseVo.setReceiverAccount(irMessageCheckSerivce.checkAccount(message.getReceiverAccount()));;
            // check currency
            irCaseVo.setCurrency(irMessageCheckSerivce.checkCurrency(message.getCurrency()));;

            //顧客資料，受通知分行
            System.out.println("message.ReceiverAccount = " + message.getReceiverAccount());
            Customer customer = irFieignClient.getCustomer(message.getReceiverAccount().substring(1,12));
//            CustomerAccount customerAccount = customerAccountService.getCustomerAccount(message.getReceiverAccount().substring(1,12));
//            Customer customer =   customerService.getCustomer(customerAccount.getCustomerSeqNo());

            irCaseVo.setBeAdvBranch(customer.getBranchID());
            irCaseVo.setCustomerID(customer.getCustomerId());
            if (message.getReceiverInfo1().length() == 0){
                irCaseVo.setReceiverInfo1(customer.getEnglishName());
            }
            //讀取匯率

            //讀取銀行名稱地址
            BankDto bankDto = irFieignClient.getBank(message.getSenderSwiftCode());
            irCaseVo.setSenderInfo1(bankDto.getName());
            irCaseVo.setSenderInfo3(bankDto.getAddress());
            //讀取都市檔

            //讀取存匯行關係

            //讀取是否為同存行

            //取號
            irSeqNo = serialNumberService.getIrSeqNo(systemType,branch);
            irCaseVo.setSeqNo(irSeqNo);


            //insert

            irCaseService.insert(irCaseVo);

            response.setCode("0000");
            response.setStatus(ResponseStatus.SUCCESS);
            response.setData(irCaseVo);
            response.setMessage(getMessage(response.getCode()));

        } catch (Exception e) {
            response.setStatus(ResponseStatus.ERROR);
            response.setCode(String.valueOf(e.getMessage()).substring(0,4));
            response.setMessage(getMessage(response.getCode()));
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
        String errorMessage = null;
        errorMessage = errorMessageService.findByErrorCode(errorCode);
        return errorMessage;

    }
}
