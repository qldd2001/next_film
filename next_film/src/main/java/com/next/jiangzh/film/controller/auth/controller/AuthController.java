package com.next.jiangzh.film.controller.auth.controller;

import com.next.jiangzh.film.controller.auth.controller.util.JwtTokenUtil;
import com.next.jiangzh.film.controller.auth.controller.vo.AuthRequstVO;
import com.next.jiangzh.film.controller.auth.controller.vo.AuthResponseVO;
import com.next.jiangzh.film.controller.common.BaseResponseVO;
import com.next.jiangzh.film.controller.exception.ParamErrorException;
import com.next.jiangzh.film.service.common.exception.CommonServiceException;
import com.next.jiangzh.film.service.user.UserServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AuthController {
    @Resource
    private UserServiceAPI userServiceAPI;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**验证用户名、密码，正确则返回新jwt token；反之登陆失败！**/
    @RequestMapping(value = "auth", method = RequestMethod.POST)
    public BaseResponseVO auth(@RequestBody AuthRequstVO authRequstVO) throws ParamErrorException, CommonServiceException {
        authRequstVO.checkParam();

        boolean isValid = userServiceAPI.userAuth(authRequstVO.getUsername(), authRequstVO.getPassword());

        if (isValid) {
            //TODO return 一个jwt token
            String randomKey = jwtTokenUtil.getRandomKey();
            String token = jwtTokenUtil.generateToken(authRequstVO.getUsername(), randomKey);

            AuthResponseVO authResponseVO = AuthResponseVO.builder()
                    .randomKey(randomKey)
                    .token(token)
                    .build();

            return BaseResponseVO.success(authResponseVO);
        } else {
            return BaseResponseVO.serviceFailed(1, "用户名或密码不正确！");
        }

    }


}
