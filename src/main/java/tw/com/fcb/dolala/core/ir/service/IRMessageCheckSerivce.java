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

    public String  getAccountNo(String account) {
        String accountNo = null;

        if (account.substring(0, 1).equals("/")) {
            accountNo = account.substring(1);

        }
        return  accountNo;
    }


    public boolean checkAccountNo(String account){
        boolean checkMK = false;
        if (account.length() == 12) {
            checkMK = true;

        } else{
            checkMK = false;
        }
        return  checkMK;
    }

    public boolean checkValueDate() {
        return true;
    }
    
    public  boolean checkAmount(){
        return  true;
    }
    
    public String checkCurrency(String currency) throws Exception {
        if (currency.length()> 3){
            throw new Exception("幣別資料有誤");
        }
        return  currency;
    }
    public boolean checkChargeType(String chargeType){
       
        return  true;
    }

 
}
