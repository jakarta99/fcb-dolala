package tw.com.fcb.dolala.core.common.http;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Access;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: DateConverterTest
 * Author: Han-Ru
 * Date: 2022/3/17 下午 04:06
 * Description:
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@SpringBootTest
class DateConverterTest {
    @Autowired
    DateConverter dateConverter;

    @Test
    void convert() {
        String date1 = "19740928";
        String date2 = "0630928";
        String date3 = "630928";

        LocalDate localDate1 = dateConverter.convert(date1);
        LocalDate localDate2 = dateConverter.convert(date2);
        LocalDate localDate3 = dateConverter.convert(date3);

        assertEquals("19740928",localDate1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        assertEquals("19740928",localDate2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        assertEquals("19740928",localDate3.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));



    }
}