package tw.com.fcb.dolala.core.ir.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.fcb.dolala.core.ir.repository.IRSwiftMessageRepository;
import tw.com.fcb.dolala.core.ir.repository.entity.IRSwiftMessage;
import tw.com.fcb.dolala.core.ir.web.cmd.SwiftMessageSaveCmd;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: IRService
 * Author: Han-Ru
 * Date: 2022/3/10 下午 03:32
 * Description: 處理電文資料
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@Transactional
@Service
public class IRService {
    @Autowired
    IRSwiftMessageRepository repository;
    public void insert(SwiftMessageSaveCmd saveCmd){
        //beginTx

        IRSwiftMessage entity = new IRSwiftMessage();
//        entity.setSeqNo(saveCmd.getSeqNo());
//        entity.setValueDate(saveCmd.getValueDate());
// 自動將saveCmd的屬性，對應到entity裡
        BeanUtils.copyProperties(saveCmd, entity);
        repository.save(entity);

        //commitTx
    }
}
