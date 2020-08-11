package com.djw.controller;

import com.alibaba.fastjson.JSONObject;
import com.djw.domain.RestfulDataResponse;
import com.djw.domain.RestfulResponse;
import com.djw.util.ResponseBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "测试接口")
public class RestApiController {

    private static String token = "aijiuxianglantianbaiyun";

    @ApiOperation(value = "测试接口1", notes = "测试Get接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value="姓名", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "age", value="年龄", required = true, dataTypeClass = Integer.class),
    })
    @GetMapping("getTest")
    public String getTest(String name, Integer age) {
        return name + age;
    }

    @ApiOperation(value = "登录测试接口", notes = "登录测试接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value="用户名", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "passWord", value="密码", required = true, dataTypeClass = String.class),
    })
    @PostMapping("loginTest")
    public RestfulResponse loginTest(String userName, String passWord) {
        JSONObject tokenJson = new JSONObject();
        tokenJson.put("token",token);
        RestfulDataResponse result = ResponseBuilder.buildOkDataResponse(tokenJson);
        return result;
    }
}