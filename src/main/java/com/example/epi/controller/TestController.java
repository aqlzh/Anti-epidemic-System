package com.example.epi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swagger")
@Api(tags = "测试接口模块", description = "主要是为了提供测试接口用的")
public class TestController {
    @GetMapping("test")
    @ApiOperation(value = "测试接口")
    public String testSwagger() {
        return "测试成功";
    }
}
