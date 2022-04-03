package tw.com.fcb.dolala.core.ir.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
@RestController
@RequestMapping("/ir")
public class IRCaseController {

    @Autowired
    IRCaseService irCaseService;
    @Autowired
    CommonFeignClient commonFeignClient;

	@PostMapping("/ircase/receive-swift")
	@Operation(description = "接收 SWIFT 電文並存到 SwiftMessage", summary = "接收及儲存 SWIFT 電文")
	public Response<String> receiveSwift(@Validated @RequestBody SwiftMessageSaveCmd message) {
		Response<String> response = new Response();
		try {
			IRCaseVo irCaseVo = new IRCaseVo();
			BeanUtils.copyProperties(message, irCaseVo);
			//讀取共用服務 set相關欄位
			irCaseService.setIRCaseData(irCaseVo);
			//insert，將電文資料新增至IRCase檔案
			String result = irCaseService.irCaseInsert(irCaseVo);
			response.Success();
			response.setData(result);
			log.info("呼叫接收 SWIFT 電文 API：接收及儲存一筆 SWIFT 電文");
		} catch (Exception e) {
			response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
			log.info("呼叫接收 SWIFT 電文 API：" + commonFeignClient.getErrorMessage(e.getMessage()));
		}
		return response;
	}
	@PutMapping("/ircase/{irSeqNo}/autopass")
	@Operation(description = "檢核電文是否可自動放行", summary = "更新AUTO_PASS欄位")
	public Response<String> checkAutoPassMK(@PathVariable("irSeqNo") String irSeqNo) {
		Response<String> response = new Response<String>();
		try {
			IRCaseDto irCaseDtoVo = irCaseService.getByIRSeqNo(irSeqNo);
			// check 相關欄位
			// update IRCaseDto
			irCaseDtoVo.setAutoPassMk("Y");
			irCaseService.updateByIRSeqNo(irCaseDtoVo);
			response.Success();
			response.setData("success");
			log.info("呼叫檢核電文是否可自動放行API：SeqNo編號" + irSeqNo);
		} catch (Exception e) {
			response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
			log.info("呼叫檢核電文是否可自動放行API：" + commonFeignClient.getErrorMessage(e.getMessage()));
		}
		return response;
	}

	@GetMapping("/ircase/{irSeqNo}/enquiry")
	@Operation(description = "取得seqNo電文資料", summary = "取得seqNo電文資料")
	public Response<IRCaseDto> getBySeqNo(@PathVariable("irSeqNo") String irSeqNo) {
		Response<IRCaseDto> response = new Response<IRCaseDto>();
		try {
			IRCaseDto irCaseDto = irCaseService.getByIRSeqNo(irSeqNo);
			response.Success();
			response.setData(irCaseDto);
			log.info("呼叫查詢匯款電文資料API：查詢SeqNo編號" + irSeqNo);
		} catch (Exception e) {
			response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
			log.info("呼叫查詢匯款電文資料API：" + commonFeignClient.getErrorMessage(e.getMessage()));
		}
		return response;
	}

}
