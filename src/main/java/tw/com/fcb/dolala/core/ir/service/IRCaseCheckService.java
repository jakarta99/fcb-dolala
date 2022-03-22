package tw.com.fcb.dolala.core.ir.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.fcb.dolala.core.common.service.CustomerAccountService;
import tw.com.fcb.dolala.core.common.service.CustomerService;
import tw.com.fcb.dolala.core.common.web.dto.Customer;
import tw.com.fcb.dolala.core.common.web.dto.CustomerAccount;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: IRCaseCheckService
 * Author: Han-Ru
 * Date: 2022/3/21 下午 02:22
 * Description: 新增IRCase前檢核匯入電文資料內容
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@Service
public class IRCaseCheckService {
    @Autowired
    CustomerAccountService customerAccountService;
    @Autowired
    CustomerService customerService;

    public CustomerAccount getCustomerAccount(String accountNo){
    // 取得受通知分行及統編
        CustomerAccount customerAccount = customerAccountService.getCustomerAccount(accountNo);
        return customerAccount;
    }
    // 取得顧客相關資訊
    public Customer getCustomerInfo(String customerId){
        Customer customer = customerService.getCustomer(customerId);
        return  customer;
    }
}
