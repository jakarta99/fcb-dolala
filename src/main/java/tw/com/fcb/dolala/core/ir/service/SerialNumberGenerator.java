package tw.com.fcb.dolala.core.ir.service;

import org.springframework.beans.factory.annotation.Autowired;
import tw.com.fcb.dolala.core.ir.repository.BranchCodeRepository;
import tw.com.fcb.dolala.core.ir.repository.entity.BranchCode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: SerialNumberGenerator
 * Author: Han-Ru
 * Date: 2022/3/11 下午 04:45
 * Description: 取號程式
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
public class SerialNumberGenerator {


    BranchCodeRepository branchCodeRepository ;

    public SerialNumberGenerator() {
    }

    public String getSeqNo(){
        Random rm=new Random();
        int strLength = 6;
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);
        String fixLenthString = String.valueOf(pross);
        String seqNo= fixLenthString.substring(1, strLength + 1);
        seqNo = getNo("1");
        return seqNo;
    }

    public String getIRNo(String branch){

        String irNo= getMoveOrderNo(branch);
        return irNo;
    }

    public String getMoveOrderNo(String branch) {
         String count = "00000";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String nowDate = sdf.format(new Date());

        String  branchCode = String.valueOf(branchCodeRepository.findByBranchCode(branch));
        String num = getNo(count);
        // S+ 西元年最末碼+ 字軋+ 流水號五碼
        num = "S"+ nowDate.substring(3,4)+ branchCode+ num;
        return num;
    }

    private static String getNo(String s) {
        String rs = s;
//        int i = Integer.parseInt(rs);
        int i = 1;
        rs = ""+ i;
        for (int j = rs.length(); j < 6; j++ ) {
            rs = "0"+ rs;
        }

        return rs;
    }
}
