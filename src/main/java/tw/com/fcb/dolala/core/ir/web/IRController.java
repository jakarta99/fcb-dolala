package tw.com.fcb.dolala.core.ir.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import tw.com.fcb.dolala.core.ir.service.IRService;
import tw.com.fcb.dolala.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.dolala.core.ir.web.dto.IR;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: IRController
 * Author: Han-Ru
 * Date: 2022/3/10 下午 02:08
 * Description: IR Controller
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@RestController
@RequestMapping("/ir")
public class IRController {

	@Autowired
	IRService service;
	
    @PostMapping
    @Operation(description = "匯入匯款主檔資料寫入", summary="新增匯入匯款主檔")
    public String insert(IRSaveCmd ir)  {
        String irNo = null;
        try {
            irNo = service.insert(ir);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return irNo;
    }

    @GetMapping("/count/{branch}")
    @Operation(description = "傳入受通知單位查詢案件數", summary="查詢案件數")
    public Integer getCount(String branch) {
        return service.getIrCaseCount(branch);
    }

    @GetMapping("/{id}")
    @Operation(description = "傳入匯入匯款編號查詢案件", summary="查詢案件")
    public IR getByIrNo(String irNo) {
        return service.findOne(irNo);
    }

    @PutMapping("/print")
    @Operation(description = "變更印製通知書記號", summary="印製通知書記號")
    public void print(String irNo) {
    	service.print(irNo);
    }

    @PutMapping("/settle")
    @Operation(description = "變更付款狀態", summary="付款狀態")
    public void settle(String irNo) {
    	service.settle(irNo);
    }
}
