package com.next.jiangzh.film.controller.auth.controller.vo;

import com.next.jiangzh.film.controller.common.BaseVO;
import com.next.jiangzh.film.controller.exception.ParamErrorException;
import lombok.Data;

@Data
public class AuthRequstVO extends BaseVO {

    private String username;
    private String password;

    @Override
    public void checkParam() throws ParamErrorException {
        //..
    }
}
