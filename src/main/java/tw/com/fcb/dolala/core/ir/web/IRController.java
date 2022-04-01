package tw.com.fcb.dolala.core.ir.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import tw.com.fcb.dolala.core.common.http.Response;
import tw.com.fcb.dolala.core.ir.http.CommonFeignClient;
import tw.com.fcb.dolala.core.ir.repository.entity.IRMaster;
import tw.com.fcb.dolala.core.ir.service.IRService;
import tw.com.fcb.dolala.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.dolala.core.ir.web.dto.IRDto;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: IRController
 * Author: Han-Ru
 * Date: 2022/3/10 下午 02:08
 * Description: IRDto Controller
 */
@Slf4j
@RestController
@RequestMapping("/ir")
public class IRController {

	@Autowired
	IRService irService;
    @Autowired
     CommonFeignClient commonFeignClient;


    @PostMapping
    @Operation(description = "匯入匯款主檔資料寫入", summary="新增匯入匯款主檔")
    public Response irMasterInsert(@Validated @RequestBody IRSaveCmd irSaveCmd) {
        Response response = new Response<>();
        String irNo = null;

        try {
            // insert irMaster檢核完成，新增至主檔
            IRMaster irMaster = irService.insertIRMaster(irSaveCmd);
            response.Success();
            response.setData(irMaster);

        } catch (Exception e) {
           response.Error(e.getMessage(),commonFeignClient.getErrorMessage(e.getMessage()));
        }
//        log.info("{}", rs.getAllErrors());
//
//        log.info("{ }", ir);

        return response;
    }

    @GetMapping("/count/{branch}")
    @Operation(description = "傳入受通知單位查詢案件數", summary="查詢案件數")
    public Integer getCount(@PathVariable String branch) {
        return irService.getIrCaseCount(branch);
    }

    @GetMapping("/{id}")
    @Operation(description = "傳入匯入匯款編號查詢案件", summary="查詢案件")
    public IRDto getByIrNo(@PathVariable String irNo) {
        return irService.findOne(irNo);
    }

    @PutMapping("/print")
    @Operation(description = "變更印製通知書記號", summary="印製通知書記號")
    public Response print(@PathVariable String irNo) {
        Response response = new Response();
    	try {
            irService.print(irNo);
            response.Success();
            response.setMessage("通知書印製成功，更新記號為Y");
        }catch(Exception e){
            response.Error(e.getMessage(),commonFeignClient.getErrorMessage(e.getMessage()));
        }
        return  response;
    }

    @PutMapping("/settle")
    @Operation(description = "變更付款狀態", summary="付款狀態")
    public void settle(@PathVariable String irNo) {
    	irService.settle(irNo);
    }
}
