package tw.com.fcb.dolala.core.ir.service;

import java.util.Random;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: SeqNoGenerator
 * Author: Han-Ru
 * Date: 2022/3/11 下午 04:45
 * Description: 取號程式
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
public class SeqNoGenerator {

    public String getSeqNo(){
        Random rm=new Random();
        int strLength = 6;
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);
        String fixLenthString = String.valueOf(pross);
        String seqNo= fixLenthString.substring(1, strLength + 1);

        return seqNo;
    }
}
