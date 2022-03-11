package tw.com.fcb.dolala.core.ir.web;

import org.springframework.web.bind.annotation.*;
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

    @PostMapping
    public void insert(IRSaveCmd ir) {

    }

    @GetMapping("/count")
    public Integer getCount(String branch) {
        return 0;
    }

    @GetMapping("/{id}")
    public IR getById(String irNo) {
        return new IR();
    }

    @PutMapping("/print")
    public void print(String irNo) {

    }

    @PutMapping("/settle")
    public void settle(String irNo) {

    }
}
