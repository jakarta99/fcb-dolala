package tw.com.fcb.dolala.core.ir.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.fcb.dolala.core.ir.repository.IRMasterRepository;
import tw.com.fcb.dolala.core.ir.repository.entity.IRMaster;
import tw.com.fcb.dolala.core.ir.web.cmd.IRSaveCmd;


/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: IRService
 * Author: sinjen
 * Date: 2022/3/10 下午 03:32
 * Description: 匯入匯款service
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@Transactional
@Service
public class IRService {
    @Autowired
    IRMasterRepository repository;

    //新增匯入匯款主檔
    public void insert(IRSaveCmd saveCmd) {
    	IRMaster irMaster = new IRMaster();
    	// 自動將saveCmd的屬性，對應到entity裡
    	BeanUtils.copyProperties(saveCmd, irMaster);
    	repository.save(irMaster);
    }
    
}
