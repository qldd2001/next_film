package com.next.jiangzh.film.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/demo")
@Api("swaggerDemoController相关的api")
public class DemoController {

    @ApiOperation(value = "测试SwaggerValue", notes = "测试SwaggerNotes")
    @ApiImplicitParam(name = "str",
            value = "入参str", paramType = "query", required = true, dataType = "string")
    @RequestMapping(value = "/test1")
    public Object test01(String str){

        System.out.println("test01="+str);

        Map<String,String> map = new HashMap<>();
        map.put("a",str);

        return map;
    }

}
