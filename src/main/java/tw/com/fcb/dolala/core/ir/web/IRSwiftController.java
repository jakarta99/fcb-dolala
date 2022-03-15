package tw.com.fcb.dolala.core.ir.web;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.fcb.dolala.core.ir.repository.IRSwiftMessageRepository;
import tw.com.fcb.dolala.core.ir.repository.entity.IRSwiftMessageEntity;
import tw.com.fcb.dolala.core.ir.service.IRSwiftMessageService;
import tw.com.fcb.dolala.core.ir.web.cmd.SwiftMessageSaveCmd;
import tw.com.fcb.dolala.core.ir.web.dto.IRSwiftMessage;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: IRSwiftController
 * Author: Han-Ru
 * Date: 2022/3/10 下午 02:51
 * Description: IRSwiftController
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@RestController
@RequestMapping("/ir")
public class IRSwiftController {

    @Autowired
    IRSwiftMessageService service;

    @PostMapping("/swift")
    @Operation(description = "接收 swift 電文並存到 SwiftMessage", summary="儲存 swift")
    public String receiveSwift(SwiftMessageSaveCmd message) {
        String irSeqNo = service.insert(message);
        return irSeqNo;
    }

    @GetMapping("/swift/{id}")
    @Operation(description = "電文檢核")
    public Boolean getValidateResult(Long id) {
        return true;
    }

//    @GetMapping("/swift/{id}")
//    @Operation(description = "取得seqNo電文資料")
//    public IRSwiftMessage getBySeqNo(String irSeqNo){
//        return service.getByIRSeqNo(irSeqNo);
//    }

}
