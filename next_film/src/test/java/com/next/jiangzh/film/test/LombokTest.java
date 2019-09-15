package com.next.jiangzh.film.test;

import com.next.jiangzh.film.example.service.bo.RegisterUserBO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class LombokTest {



    @Test
    public void lombokaaatest(){
        RegisterUserBO registerUserBO = new RegisterUserBO();
        registerUserBO.setUsername("a1");
        registerUserBO.setUserpwd("aa2");
        registerUserBO.setUuid("aaa3");

        System.out.println("registerUserBO = " + registerUserBO);

    }

    @Test
    public void lombokBuildTest(){
        RegisterUserBO registerUserBO =RegisterUserBO.builder().uuid("001")
                .username("Shaka")
                .userpwd("heihei")
                .build();

        System.out.println("registerUserBO = " + registerUserBO);
        log.info("aaaa = " + registerUserBO);
    }
}
