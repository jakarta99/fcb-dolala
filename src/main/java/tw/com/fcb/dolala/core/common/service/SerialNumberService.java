package tw.com.fcb.dolala.core.common.service;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Transactional
@Service
public class SerialNumberService {

    @Autowired
    BranchInformationRepository branchInformationRepository;
    @Autowired
    SerialNumberRepository serialNumberRepository;


//取得irCase seqNO
    public String getIrSeqNo(String systemType,String branch) throws Exception {

        SerialNumber serialNumber;
        serialNumber = serialNumberRepository.findBySystemTypeAndBranch(systemType,branch).orElseThrow(() -> new Exception("找不到取號檔資料"+ systemType + branch));
        String seqNo = combinationIrSeq(getNo(serialNumber.getSerialNo()));

        log.info("{讀取IR_SEQ 取號檔號碼 =} " +serialNumber.getSerialNo());
        log.info("{取得IR_SEQ NO = }" +seqNo);
        return seqNo;
    }
//取得外匯編號FxNo
    public String getFxNo(String noCode,String systemType,String branch) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String nowDate = sdf.format(new Date());
        //取得字軌
        BranchInformation branchInformation = branchInformationRepository.findByBranch(branch).orElseThrow(() -> new Exception("找不到此分行別"+ branch));
        String branchCode = branchInformation.getBranchCode();
        System.out.println("BranchInformation !!! line " + branchCode );
        //讀取取號檔
        SerialNumber serialNumber;
        serialNumber = serialNumberRepository.findBySystemTypeAndBranch(systemType,branch).orElseThrow(() -> new Exception("找不到取號檔資料"+ systemType + branch));
        System.out.println("SerialNumberService !!! line 53" + serialNumber );
        log.info("讀取取號檔號碼 = " +serialNumber.getSerialNo());
        //取得流水號
        Long serialNo = getNo(serialNumber.getSerialNo());
        //取得外匯編號
        String fxNo = combinationFxNo(noCode,nowDate.substring(3,4),branchCode,serialNo) ;
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

// 取得流水號
    private static Long getNo(Long s) {

        Long serialNo;
        int i = 1;
        serialNo = s + i;
        return serialNo;
    }
    // noCode + 西元年最末碼+ 字軋+ 流水號 = 共10碼
    private static String combinationFxNo(String noCode,String year,String branchCode,Long serialNo) {
        String fxNo;
        String tempNo = String.valueOf(serialNo);
        fxNo = noCode + year + branchCode;
        int length = 10 - fxNo.length();

        for (int j = 1; j < length; j++ ) {
             tempNo = "0"+ tempNo;
        }
        fxNo = fxNo + tempNo;
        return fxNo;
    }
    // 取號
    private static String combinationIrSeq(Long serialNo) {
        String irSeq;
        String tempNo = String.valueOf(serialNo);

        int length = 6 - tempNo.length();

        for (int j = 1; j <= length; j++ ) {
            tempNo = "0"+ tempNo;
        }
        irSeq =  tempNo;
        return irSeq;
    }
}
