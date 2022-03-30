package tw.com.fcb.dolala.core.common.http;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import tw.com.fcb.dolala.core.common.enums.ResponseStatus;
import tw.com.fcb.dolala.core.ir.http.CommonFeignClient;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: Response
 * Author: Han-Ru
 * Date: 2022/3/17 下午 04:21
 * Description: 回應前端Object
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@Data


public class Response<T> {


    ResponseStatus status;
    String code;
    String message;
    T data;

    public void Success(){
        setStatus(ResponseStatus.SUCCESS);
        setCode("0000");
        setMessage(null);
    }

    public void Error(String code,String message){
        setStatus(ResponseStatus.ERROR);
        setCode(code.substring(0,4));
        setMessage(message);
    }
}
