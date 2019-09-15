package com.next.jiangzh.film.service.user;

import com.next.jiangzh.film.controller.user.vo.EnrollUserVO;
import com.next.jiangzh.film.controller.user.vo.UserInfoVO;
import com.next.jiangzh.film.service.common.exception.CommonServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    @Resource
    private UserServiceAPI userServiceAPI;

    @Test
    public void userEnroll() throws CommonServiceException {
        EnrollUserVO enrollUserVO = new EnrollUserVO();
        enrollUserVO.setUsername("shaka");
        enrollUserVO.setAddress("addaaaaa");
        enrollUserVO.setEmail("qq.mail");
        enrollUserVO.setPassword("heihei");

        userServiceAPI.userEnroll(enrollUserVO);
    }

    @Test
    public void checkUserName() {




    }

    @Test
    public void userAuth() {
    }

    @Test
    public void describeUserInfo() throws CommonServiceException {
        String userId="2";
        System.out.println(userServiceAPI.describeUserInfo(userId));
    }

    @Test
    public void updateUserInfo() throws CommonServiceException {
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUuid(6);
        userInfoVO.setUsername("next");
        System.out.println(userServiceAPI.updateUserInfo(userInfoVO));
    }




}