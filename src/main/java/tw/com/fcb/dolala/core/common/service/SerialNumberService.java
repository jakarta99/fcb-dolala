package tw.com.fcb.dolala.core.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.fcb.dolala.core.common.repository.entity.BranchInformation;
import tw.com.fcb.dolala.core.common.repository.entity.SerialNumber;
import tw.com.fcb.dolala.core.common.repository.BranchInformationRepository;
import tw.com.fcb.dolala.core.common.repository.SerialNumberRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: SerialNumberService
 * Author: Han-Ru
 * Date: 2022/3/11 下午 04:45
 * Description: 取號程式
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */

@Transactional
@Service
public class SerialNumberService {

    @Autowired
    BranchInformationRepository branchInformationRepository;
    @Autowired
    SerialNumberRepository serialNumberRepository;



    public String getIrSeqNo(String systemType,String branch){

        SerialNumber serialNumber;
        serialNumber = serialNumberRepository.getBySystemTypeAndBranch(systemType,branch);
        String seqNo = getNo(serialNumber.getSerialNo());
        return seqNo;
    }

    public String getFxNo(String noCode,String systemType,String branch) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String nowDate = sdf.format(new Date());
        //取得字軌
        BranchInformation branchInformation = branchInformationRepository.findByBranch(branch).orElseThrow(() -> new Exception("找不到此分行別"+ branch));
        String branchCode = branchInformation.getBranchCode();
        //讀取取號檔
        SerialNumber serialNumber;
        serialNumber = serialNumberRepository.getBySystemTypeAndBranch(systemType,branch);
        //取得流水號
        String serialNo = getNo(serialNumber.getSerialNo());
        // noCode + 西元年最末碼+ 字軋+ 流水號六碼
        String fxNo = noCode + nowDate.substring(3,4)+ branchCode+ serialNo;
        return fxNo;
    }



    private static String getNo(Long s) {
        String serialNo;
        Long rsTemp;
        int i = 1;
        rsTemp = s + i;
        serialNo = String.valueOf(rsTemp);
        for (int j = serialNo.length(); j < 6; j++ ) {
            serialNo = "0"+ serialNo;
        }

        return serialNo;
    }

    public void updateSerialNumber(SerialNumber serialNumber,Long serialNo){
        serialNumber.setSerialNo(serialNo);
        //更新取號檔
        serialNumberRepository.save(serialNumber);
    }
}
