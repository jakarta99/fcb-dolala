package tw.com.fcb.dolala.core.ir.web;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tw.com.fcb.dolala.core.common.http.Response;
import tw.com.fcb.dolala.core.ir.http.CommonFeignClient;
import tw.com.fcb.dolala.core.ir.service.IRCaseService;
import tw.com.fcb.dolala.core.ir.vo.IRCaseVo;
import tw.com.fcb.dolala.core.ir.web.cmd.SwiftMessageSaveCmd;
import tw.com.fcb.dolala.core.ir.web.dto.IRCaseDto;

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
    CommonFeignClient commonFeignClient;

    @PostMapping("/ircase")
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

    @PutMapping("/ircase/{irSeqNo}/autopass")
    @Operation(description = "檢核是否可自動放行", summary="更新AUTO_PASS欄位")
    public Response checkAutoPassMK(@PathVariable("irSeqNo") String  irSeqNo) {
        Response response = new Response();
        try {
            IRCaseDto irCaseDtoVo = irCaseService.getByIRSeqNo(irSeqNo);
            // check 相關欄位
            // update IRCaseDto
            irCaseDtoVo.setAutoPassMk("Y");
            irCaseService.updateByIRSeqNo(irCaseDtoVo);
            response.Success();
            response.setData("success");
        }catch (Exception e) {
            response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));

        }
        return response;
    }

    @GetMapping("/{irSeqNo}/ircase")
    @Operation(description = "取得seqNo電文資料",summary="取得seqNo電文資料")
    public Response getBySeqNo(@PathVariable("irSeqNo") String irSeqNo){
        Response response = new Response();
        try {
            IRCaseDto irCaseDto = irCaseService.getByIRSeqNo(irSeqNo);
            response.Success();
            response.setData(irCaseDto);
        }catch(Exception e){
            response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
        }
        return response;
    }

}
