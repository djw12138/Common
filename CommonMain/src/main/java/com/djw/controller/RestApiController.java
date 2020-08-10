package com.djw.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "测试接口")
public class RestApiController {

    @ApiOperation(value = "测试接口1", notes = "测试Get接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value="姓名", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "age", value="年龄", required = true, dataTypeClass = Integer.class),
    })
    @GetMapping("getTest")
    public String getTest(String name, Integer age) {
        return name + age;
    }
}