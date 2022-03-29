package tw.com.fcb.dolala.core.ir.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import tw.com.fcb.dolala.core.common.repository.entity.ExchgRate;
import tw.com.fcb.dolala.core.common.service.ExchgRateService;
import tw.com.fcb.dolala.core.common.service.SerialNumberService;
import tw.com.fcb.dolala.core.ir.http.CommonFeignClient;
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
@Slf4j
@RestController
@RequestMapping("/ir")
public class IRController {

	@Autowired
	IRService irService;
    @Autowired
    ExchgRateService rateService;
    @Autowired
    SerialNumberService serialNumberService;
    final CommonFeignClient commonFeignClient;
    //取號檔 SystemType,branch
    private final String systemType = "IR";
    private final String noCode = "S";

    public IRController(CommonFeignClient commonFeignClient) {
        this.commonFeignClient = commonFeignClient;
    }

    @PostMapping
    @Operation(description = "匯入匯款主檔資料寫入", summary="新增匯入匯款主檔")
    public String insert(IRSaveCmd ir, BindingResult rs) {

        String irNo = null;

        try {
            // 從匯率資料檔取得ExchgRate
//            ir.setExchangeRate(rateService.getRate(ExchgRate.EXCHG_RATE_TYPE_BUY, ir.getCurency(), "TWD"));

            ir.setExchangeRate(commonFeignClient.getFxRate(ExchgRate.EXCHG_RATE_TYPE_BUY, ir.getCurency(),"TWD"));
            //取號
            String branch = ir.getBeAdvBranch();
            irNo = commonFeignClient.getFxNo(noCode,systemType,ir.getBeAdvBranch());
//            irNo = serialNumberService.getFxNo("S", "IR", ir.getBeAdvBranch());
            ir.setIrNo(irNo);


            // insert irMaster檢核完成，新增至主檔
            irService.insertIRMaster(ir);
            Process log;


        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("{}", rs.getAllErrors());

        log.info("{ }", ir);

        return irNo;
    }

    @GetMapping("/count/{branch}")
    @Operation(description = "傳入受通知單位查詢案件數", summary="查詢案件數")
    public Integer getCount(String branch) {
        return irService.getIrCaseCount(branch);
    }

    @GetMapping("/{id}")
    @Operation(description = "傳入匯入匯款編號查詢案件", summary="查詢案件")
    public IR getByIrNo(String irNo) {
        return irService.findOne(irNo);
    }

    @PutMapping("/print")
    @Operation(description = "變更印製通知書記號", summary="印製通知書記號")
    public void print(String irNo) {
    	irService.print(irNo);
    }

    @PutMapping("/settle")
    @Operation(description = "變更付款狀態", summary="付款狀態")
    public void settle(String irNo) {
    	irService.settle(irNo);
    }
}
