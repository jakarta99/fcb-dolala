package tw.com.fcb.dolala.core.common.http;

import lombok.Data;
import tw.com.fcb.dolala.core.common.enums.ResponseStatus;

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

}
