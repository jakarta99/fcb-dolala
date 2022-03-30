package tw.com.fcb.dolala.core.ir.web;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tw.com.fcb.dolala.core.common.http.Response;
import tw.com.fcb.dolala.core.common.service.*;
import tw.com.fcb.dolala.core.ir.http.CommonFeignClient;
import tw.com.fcb.dolala.core.ir.service.IRCaseService;
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
    ErrorMessageService errorMessageService;
    @Autowired
    CommonFeignClient commonFeignClient;

    @PostMapping("/swift")
    @Operation(description = "接收 swift 電文並存到 SwiftMessage", summary="儲存 swift")
    public Response receiveSwift(@Validated @RequestBody SwiftMessageSaveCmd message) {
        //取號
        String irSeqNo = null;
        Response response = new Response<>();
        try {
            IRCaseVo irCaseVo = new IRCaseVo();
            BeanUtils.copyProperties(message, irCaseVo);
            //讀取共用服務 set相關欄位
            irCaseService.setIRCaseData(irCaseVo);
            //insert，將電文資料新增至IRCase檔案
            irCaseService.irCaseInsert(irCaseVo);
            response.Success();
            response.setData(irCaseVo);

        } catch (Exception e) {
            response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
        }
        return response;
    }

    @GetMapping("/swift/{id}")
    @Operation(description = "檢核是否可自動放行", summary="更新AUTO_PASS欄位")
    public Response checkAutoPassMK(String  irSeqNo) {
        Response response = new Response();
        try {
            irCaseService.getByIRSeqNo(irSeqNo);
            // check 相關欄位
            response.Success();
            response.setData("success");
        }catch (Exception e) {
            response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));

        }
        return response;
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
            response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
        }
        return errorMessage;
    }
}
