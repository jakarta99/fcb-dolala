package tw.com.fcb.dolala.core.ir.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.fcb.dolala.core.common.service.SerialNumberGenerator;
import tw.com.fcb.dolala.core.ir.repository.IRSwiftMessageRepository;
import tw.com.fcb.dolala.core.common.repository.SerialNumberRepository;
import tw.com.fcb.dolala.core.ir.repository.entity.IRSwiftMessageEntity;
import tw.com.fcb.dolala.core.common.repository.entity.SerialNumber;
import tw.com.fcb.dolala.core.ir.web.cmd.SwiftMessageSaveCmd;
import tw.com.fcb.dolala.core.ir.web.dto.IRSwiftMessage;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: IRSwiftMessageService
 * Author: Han-Ru
 * Date: 2022/3/11 下午 05:30
 * Description: 匯入電文service
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@Transactional
@Service
public class IRSwiftMessageService {
    @Autowired
    IRSwiftMessageRepository repository;
    @Autowired
    SerialNumberGenerator serialNumberGenerator;
    @Autowired
    SerialNumberRepository serialNumberRepository;
    //取號檔 SystemType,branch
    private final String systemType = "IR_SEQ";
    private final String branch = "999";
    public String insert(SwiftMessageSaveCmd saveCmd){
        //beginTx

        IRSwiftMessageEntity irMessageEntity = new IRSwiftMessageEntity();
        saveCmd.setSeqNo(serialNumberGenerator.getIrSeqNo(systemType,branch));
// 自動將saveCmd的屬性，對應到entity裡
        BeanUtils.copyProperties(saveCmd, irMessageEntity);
         repository.save(irMessageEntity);
        //更新取號檔
        SerialNumber serialNumber;
        serialNumber = serialNumberRepository.getBySystemTypeAndBranch(systemType,branch);
        String serialNo = irMessageEntity.getSeqNo();
        serialNumberGenerator.updateSerialNumber(serialNumber, Long.valueOf(serialNo));
        //commitTx
        return irMessageEntity.getSeqNo();
    }

    //傳入匯入匯款編號查詢案件
    public IRSwiftMessage getByIRSeqNo(String irSeqNo) {

        IRSwiftMessageEntity irSwiftMessageEntity = repository.findBySeqNo(irSeqNo);

        IRSwiftMessage irSwiftMessage = new IRSwiftMessage();

        if (irSwiftMessage != null) {
            // 自動將entity的屬性，對應到dto裡
            BeanUtils.copyProperties(irSwiftMessageEntity, irSwiftMessage);
        }
        return irSwiftMessage;
    }

}