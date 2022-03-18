package tw.com.fcb.dolala.core.common.http;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: DateConverter
 * Author: Han-Ru
 * Date: 2022/3/17 下午 03:42
 * Description: 日期轉換
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@Component
public class DateConverter  implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source){
        if (source == null) {
            return LocalDate.now();
        }
        if (source.length() == 8){
            return LocalDate.of(Integer.valueOf(source.substring(0,4)),
                    Integer.valueOf(source.substring(4,6)),
                    Integer.valueOf(source.substring(6,8)));
        }else if (source.length() == 7){
            String rocYear = source.substring(0,3);
            return LocalDate.of(Integer.parseInt(rocYear)+1911,
                    Integer.valueOf(source.substring(3,5)),
                    Integer.valueOf(source.substring(5,7)));
        }else if (source.length() == 6){
            String rocYear = source.substring(0,2);
            return LocalDate.of(Integer.parseInt(rocYear)+1911,
                    Integer.valueOf(source.substring(3,5)),
                    Integer.valueOf(source.substring(5,7)));
        }
        return  null;
    }

}
