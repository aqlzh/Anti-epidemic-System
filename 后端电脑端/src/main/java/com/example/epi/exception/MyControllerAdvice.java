package com.example.epi.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyControllerAdvice {

    @ResponseBody
    @ExceptionHandler(value = UnauthorizedException.class)
    public Map<String, Object> unAuthorizationErrorHandler(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 400);
        map.put("string", "无权限访问该页面");
        map.put("msg", e.getMessage());
        System.out.println("com.example.epi.exception-----UnAuthorizationException:---"+map);
        return map;
    }

    @ResponseBody
    @ExceptionHandler(value = AuthorizationException.class)
    public Map<String, Object> AuthorizationErrorHandler(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 400);
        map.put("string", "权限认证失败");
        map.put("msg", e.getMessage());
        System.out.println("com.example.epi.exception-----AuthorizationException:---"+map);
        return map;
    }

}
