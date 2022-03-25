package tw.com.fcb.dolala.core.ir.service;

import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.fcb.dolala.core.ir.repository.enums.ChargeType;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: IRMessageCheckSerivce
 * Author: Han-Ru
 * Date: 2022/3/25 下午 03:24
 * Description:電文欄位基本檢核
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@Service
@Transactional
@Builder
public class IRMessageCheckSerivce {

    public String  checkAccount(String account){
        String accountNo = null;
        if (account.substring(0,1).equals("/")){
                accountNo = account.substring(1);
            
        }
        return  accountNo;
    }

    public boolean checkValueDate() {
        return true;
    }
    
    public  boolean checkAmount(){
        return  true;
    }
    
    public String checkCurrency(String currency){

        return  currency;
    }
    public boolean checkChargeType(String chargeType){
       
        return  true;
    }

 
}
