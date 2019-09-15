package com.next.jiangzh.film.controller.user;

import com.next.jiangzh.film.common.utils.ToolUtils;
import com.next.jiangzh.film.controller.common.BaseResponseVO;
import com.next.jiangzh.film.controller.common.TraceUtil;
import com.next.jiangzh.film.controller.exception.NextFilmException;
import com.next.jiangzh.film.controller.exception.ParamErrorException;
import com.next.jiangzh.film.controller.user.vo.EnrollUserVO;
import com.next.jiangzh.film.controller.user.vo.UserInfoVO;
import com.next.jiangzh.film.service.common.exception.CommonServiceException;
import com.next.jiangzh.film.service.user.UserServiceAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user/")
@Api("用户模块相关的API")
public class UserController {
    @Resource
    private UserServiceAPI userServiceAPI;

    @ApiOperation(value = "用户名重复性验证",notes = "用户名重复性验证")
    @ApiImplicitParam(name="username"
            ,value = "待验证的用户名称"
            ,paramType = "query",required = true,dataType = "String")
    @RequestMapping(value = "check", method = RequestMethod.POST)
    public Object checkUser(String username) throws CommonServiceException, NextFilmException {
//        if (Optional.ofNullable(username).isPresent()){
        if (ToolUtils.isEmpty(username)) {
            throw new NextFilmException(1, "username不能为空！");
        }

        boolean hasUserName = userServiceAPI.checkUserName(username);

        if (hasUserName) {
            return BaseResponseVO.serviceFailed("用户名已存在");
        } else {
            return BaseResponseVO.success();
        }
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Object register(@RequestBody EnrollUserVO enrollUserVO) throws CommonServiceException, NextFilmException, ParamErrorException {
        //领域模型：贫血模型--充血模型
        enrollUserVO.checkParam();

        userServiceAPI.userEnroll(enrollUserVO);

        return BaseResponseVO.success();
    }

    @RequestMapping(value = "getUserInfo", method = RequestMethod.GET)
    public Object describeUserInfo() throws CommonServiceException, ParamErrorException {
        String userId = TraceUtil.getUserId();

        UserInfoVO userInfoVO = userServiceAPI.describeUserInfo(userId);

        userInfoVO.checkParam();

        return BaseResponseVO.success( userInfoVO );
    }

    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
    public BaseResponseVO updateUserInfo(@RequestBody UserInfoVO userInfoVO) throws CommonServiceException, ParamErrorException {
        userInfoVO.checkParam();

        UserInfoVO result = userServiceAPI.updateUserInfo(userInfoVO);

        return BaseResponseVO.success( userInfoVO );
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public BaseResponseVO logout()  {
        String userId = TraceUtil.getUserId();

        /**
         * 1、用户信息放入redis换成
         * 2、去掉用户缓存
         * */
        TraceUtil.removeUserId(userId);

        return BaseResponseVO.success(  );
    }

}
