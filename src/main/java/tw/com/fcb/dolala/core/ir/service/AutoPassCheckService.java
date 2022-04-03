package tw.com.fcb.dolala.core.ir.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.fcb.dolala.core.ir.web.dto.IRCaseDto;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: AutoPassCheckService
 * Author: Han-Ru
 * Date: 2022/3/25 下午 02:18
 * Description:檢核是否可自動放行
 */
@Service
@Transactional
public class AutoPassCheckService {

    public String checkAutoPass(IRCaseDto irCaseDto){
        // check 相關欄位
        // update IRCaseDto
        boolean checkBranch = this.checkBranch(irCaseDto);
        boolean checkCurrencyNotTWD = this.checkCurrencyNotTWD(irCaseDto);
        boolean checkClosed = this.checkClosed(irCaseDto);
        System.out.println("checkBranch = "+ checkBranch );
        System.out.println("checkCurrencyNotTWD = "+ checkCurrencyNotTWD );
        System.out.println("checkClosed = "+ checkClosed );
        if (checkBranch == true && checkCurrencyNotTWD == true && checkClosed == true) {
            return  "Y";
        }else{
            return "N" ;
        }
    }
    public  boolean checkCurrencyNotTWD(IRCaseDto irCaseDto){
        if (irCaseDto.getCurrency().equals("TWD")) {
            return false;
        }else{
            return true;
        }
    }

    public boolean checkBranch(IRCaseDto irCaseDto){
        if (irCaseDto.getBeAdvBranch()== null){
            return  false;
        }else{
            return  true;
        }
    }
    public boolean checkClosed(IRCaseDto irCaseDto){
        if (irCaseDto.getProcessStatus().equals("7")){
            return  false;
        }else{
            return  true;
        }
    }
}
