package tw.com.fcb.dolala.core.common.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@Slf4j
@Transactional
@Service
public class SerialNumberService {
    @Value("${fcb.dolala.core.serial-no-code}")
    String noCode;

    @Autowired
    BranchInformationRepository branchInformationRepository;
    @Autowired
    SerialNumberRepository serialNumberRepository;


//取得irCase seqNO
    public String getIrSeqNo(String systemType,String branch) throws Exception {

        SerialNumber serialNumber;
        serialNumber = serialNumberRepository.findBySystemTypeAndBranch(systemType,branch).orElseThrow(() -> new Exception("D001"+ "SerialNumberRepository" + systemType +"," + branch));
        String seqNo = getNo(serialNumber.getSerialNo());
        return seqNo;
    }
//取得外匯編號FxNo
    public String getFxNo(String systemType,String branch) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String nowDate = sdf.format(new Date());
        //取得字軌
        BranchInformation branchInformation = branchInformationRepository.findByBranch(branch).orElseThrow(() -> new Exception("D001"+ "branchInformationRepository"+ branch));
        String branchCode = branchInformation.getBranchCode();
        System.out.println("BranchInformation !!! line " + branchCode );
        //讀取取號檔
        SerialNumber serialNumber;
        serialNumber = serialNumberRepository.findBySystemTypeAndBranch(systemType,branch).orElseThrow(() -> new Exception("D001"+ "SerialNumberRepository"+ systemType + branch));
        log.info("讀取取號檔號碼 = " +serialNumber.getSerialNo());
        //取得流水號
        String serialNo = getNo(serialNumber.getSerialNo());
        // noCode + 西元年最末碼+ 字軋+ 流水號六碼
        log.info("no-code = {}",noCode);
        String fxNo = noCode + nowDate.substring(3,4)+ branchCode+ serialNo;


        log.info("{取得外匯編號 }"+ fxNo);

        return fxNo;
    }
 // 讀取取號檔資料
    public  SerialNumber getNumberSerial(String systemType,String branch){
        SerialNumber serialNumber;
        serialNumber = serialNumberRepository.findBySystemTypeAndBranch(systemType,branch).orElseThrow();
        return serialNumber;
    }
    //更新取號檔
    public void updateSerialNumber(String systemType,String branch,Long serialNo){
        SerialNumber serialNumber = this.getNumberSerial(systemType,branch);

        serialNumber.setSerialNo(serialNo);
        //更新取號檔
        serialNumberRepository.save(serialNumber);
    }

// 取號 + 1
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

}
