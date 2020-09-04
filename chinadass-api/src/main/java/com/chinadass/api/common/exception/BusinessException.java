package com.chinadass.api.common.exception;

import com.chinadass.api.common.enums.ResultCode;
import lombok.Data;

/**
 * 自定义异常
 */
@Data
public class BusinessException extends RuntimeException{

    public Integer code;

    public String message;


    public BusinessException(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
    }


}
