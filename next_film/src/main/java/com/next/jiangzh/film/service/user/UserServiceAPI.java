package com.next.jiangzh.film.service.user;

import com.next.jiangzh.film.controller.user.vo.EnrollUserVO;
import com.next.jiangzh.film.controller.user.vo.UserInfoVO;
import com.next.jiangzh.film.service.common.exception.CommonServiceException;

public interface UserServiceAPI {
    /**用户登记*/
    void userEnroll(EnrollUserVO enrollUserVO) throws CommonServiceException;

    /**验证用户名是否存储*/
    boolean checkUserName(String userName) throws CommonServiceException;

    /**用户密码验证*/
    boolean userAuth(String userName,String userPwd) throws  CommonServiceException;

    /**获取用户信息*/
    UserInfoVO describeUserInfo(String userId) throws  CommonServiceException;

    /**修改用户信息*/
    UserInfoVO updateUserInfo(UserInfoVO userInfoVO) throws  CommonServiceException;


}
