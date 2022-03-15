package tw.com.fcb.dolala.core.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.fcb.dolala.core.common.repository.BranchInformationRepository;
import tw.com.fcb.dolala.core.common.repository.entity.BranchInformation;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: BranchCheckService
 * Author: Han-Ru
 * Date: 2022/3/15 下午 04:59
 * Description: 分行別檢核
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@Transactional
@Service
public class BranchCheckService {
    @Autowired
    BranchInformationRepository branchInformationRepository;

    public boolean checkBranchExist(String branch){
        BranchInformation branchInformation =
                  branchInformationRepository.getById(branch);
        if (branchInformation == null){
            return  false;
        }else {
            return true;
        }
    }
}
