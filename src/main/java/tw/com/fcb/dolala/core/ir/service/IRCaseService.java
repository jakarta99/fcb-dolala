package tw.com.fcb.dolala.core.ir.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.fcb.dolala.core.common.service.CustomerAccountService;
import tw.com.fcb.dolala.core.common.service.CustomerService;
import tw.com.fcb.dolala.core.common.web.dto.Customer;
import tw.com.fcb.dolala.core.common.web.dto.CustomerAccount;
import tw.com.fcb.dolala.core.ir.repository.IRCaseRepository;
import tw.com.fcb.dolala.core.ir.repository.entity.IRCaseEntity;
import tw.com.fcb.dolala.core.ir.vo.IRCaseVo;
import tw.com.fcb.dolala.core.ir.web.cmd.SwiftMessageSaveCmd;
import tw.com.fcb.dolala.core.ir.web.dto.IRCase;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: IRCaseService
 * Author: Han-Ru
 * Date: 2022/3/11 下午 05:30
 * Description: 匯入電文service
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@Transactional
@Service
public class IRCaseService {
    @Autowired
    IRCaseRepository irCaseRepository;



    @Autowired
    CustomerAccountService customerAccountService;
    @Autowired
    CustomerService customerService;

    //取號檔 SystemType,branch
    private final String systemType = "IR_SEQ";
    private final String branch = "999";


    public boolean insert(IRCaseVo irCaseVo){
        //beginTx


        IRCaseEntity irCaseEntity = new IRCaseEntity();

// 將irCaseVo，對應到entity裡
        BeanUtils.copyProperties(irCaseVo, irCaseEntity);
        irCaseRepository.save(irCaseEntity);

        //commitTx
        return true;
    }

    public IRCaseVo  saveIRCaseData(SwiftMessageSaveCmd saveCmd){
        IRCaseVo irCaseVo = new IRCaseVo();
        CustomerAccount customerAccount = customerAccountService.getCustomerAccount(saveCmd.getReceiverAccount().substring(1,12));
        Customer customer =   customerService.getCustomer(customerAccount.getCustomerSeqNo());
        BeanUtils.copyProperties(saveCmd, irCaseVo);
        irCaseVo.setBeAdvBranch(customerAccount.getBranchID());
        irCaseVo.setCustomerID(customer.getCustomerId());
        if (saveCmd.getReceiverInfo1().length() == 0){
            irCaseVo.setReceiverInfo1(customer.getEnglishName());
        }
        return irCaseVo;
    }

    //傳入seqNo編號查詢案件
    public IRCase getByIRSeqNo(String irSeqNo) {

        IRCaseEntity irCaseEntity = irCaseRepository.findBySeqNo(irSeqNo);

        IRCase irCase = new IRCase();

        if (irCase != null) {
            // 自動將entity的屬性，對應到dto裡
            BeanUtils.copyProperties(irCaseEntity, irCase);
        }
        return irCase;
    }

}