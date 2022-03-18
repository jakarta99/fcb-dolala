package tw.com.fcb.dolala.core.ir.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.fcb.dolala.core.common.service.SerialNumberService;
import tw.com.fcb.dolala.core.ir.repository.IRCaseRepository;
import tw.com.fcb.dolala.core.common.repository.SerialNumberRepository;
import tw.com.fcb.dolala.core.ir.repository.entity.IRCaseEntity;
import tw.com.fcb.dolala.core.common.repository.entity.SerialNumber;
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
    SerialNumberService serialNumberService;
    @Autowired
    SerialNumberRepository serialNumberRepository;
    //取號檔 SystemType,branch
    private final String systemType = "IR_SEQ";
    private final String branch = "999";


    public boolean insert(SwiftMessageSaveCmd saveCmd){
        //beginTx

        IRCaseEntity irCaseEntity = new IRCaseEntity();
// 自動將saveCmd的屬性，對應到entity裡
        BeanUtils.copyProperties(saveCmd, irCaseEntity);
        irCaseRepository.save(irCaseEntity);

        //commitTx
        return true;
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