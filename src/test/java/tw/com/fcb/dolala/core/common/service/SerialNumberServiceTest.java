package tw.com.fcb.dolala.core.common.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.fcb.dolala.core.common.repository.SerialNumberRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: SerialNumberServiceTest
 * Author: Han-Ru
 * Date: 2022/3/21 下午 01:43
 * Description:
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@SpringBootTest
class SerialNumberServiceTest {
    @Autowired
    SerialNumberRepository serialNumberRepository;


    @Test
    void getIrSeqNo() {
        String seqNo = combinationIrSeq(1L);
        assertEquals("000001",seqNo);

    }

    private String combinationIrSeq(long serialNo) {
        String irSeq;
        String tempNo = String.valueOf(serialNo);

        int length = 6 - tempNo.length();

        for (int j = 1; j <= length; j++ ) {
            tempNo = "0"+ tempNo;
        }
        irSeq =  tempNo;
        return irSeq;
    }

    @Test
    void getFxNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String nowDate = sdf.format(new Date());
        //取得外匯編號
        String fxNo = combinationFxNo("S",nowDate.substring(3,4),"AW",1) ;
        assertEquals("S2AW000001",fxNo);
    }

    private String combinationFxNo(String noCode, String year, String branchCode, int serialNo) {
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

}