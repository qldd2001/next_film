package com.next.jiangzh.film.controller.exception;

import lombok.Data;

/**
 * vo参数验证异常
 * */
@Data
public class ParamErrorException extends Exception {
    private Integer code;
    private String errMsg;

    public ParamErrorException(Integer code, String errMsg) {
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }

}
