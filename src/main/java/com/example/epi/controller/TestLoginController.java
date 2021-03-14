package com.example.epi.controller;

import com.example.epi.constants.Constant;
import com.example.epi.model.User;
import com.example.epi.service.UserService;
import com.example.epi.util.JwtUtil;
import com.example.epi.util.RedisUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//@RestController
@Controller
public class TestLoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/")
    public String login() {
        System.out.println("---------getLogin-------------");
        return "login";
    }

    @PostMapping("/doLogin")
    //@ResponseBody
    public String doLogin(String username, String password, HttpServletResponse response) {
        System.out.println("------------postLogin-------------");
        //User user = new User();
        User user = userService.getUserInfo(username, password);
        //查询失败
        if (user == null) {
            //return Result.fail("用户名或密码错误");
            System.out.println("用户名或密码错误");
        } else {
            //成功
            Map<String,Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("user", user.getUsername());
            String token = jwtUtil.getAccessToken(user.getUsername(), claims);  //签发Token
            redisUtil.set(Constant.JWT_REFRESH_KEY+user.getId(), token, jwtUtil.refresh_expire_time, TimeUnit.MILLISECONDS);
            System.out.println(token);
            response.setHeader(Constant.ACCESS_TOKEN, token);
            response.setHeader("Access-Control-Expose-Headers", Constant.ACCESS_TOKEN);

            Claims claimsByToken = jwtUtil.getClaimsFromToken(token);
            System.out.println(claimsByToken);
        }
        return "success";
    }

    //@PostMapping("/admin")
    //@ResponseBody
    @RequiresRoles("admin")
    @GetMapping("/admin")
    public String getAdmin() {
        System.out.println("--------getAdmin-----------");
        return "success";
    }

    @GetMapping("/success")
    public void success() {
        System.out.println("----------SUCCESS---------");
    }
}
