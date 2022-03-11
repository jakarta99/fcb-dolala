package tw.com.fcb.dolala.core.ir.web;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.fcb.dolala.core.ir.service.IRService;
import tw.com.fcb.dolala.core.ir.web.cmd.IRCriteriaCmd;
import tw.com.fcb.dolala.core.ir.web.cmd.SwiftMessageSaveCmd;

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
    IRService service;

    @PostMapping("/swift")
    @Operation(description = "接收 swift 電文並存到 SwiftMessage", summary="儲存 swift")
    public void receiveSwift(SwiftMessageSaveCmd message) {

        service.insert(message);
    }

    @GetMapping("/swift/{id}")
    public Boolean getValidateResult(Long id) {
        return true;
    }

}
