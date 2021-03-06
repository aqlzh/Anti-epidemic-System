package com.example.epi.controller;

import com.example.epi.model.common.Result;
import com.example.epi.model.User;
import com.example.epi.service.UserService;
import com.example.epi.util.RedisUtil;
import com.example.epi.util.TokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@RestController
public class LoginController {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public Result login(String username, String password, HttpServletResponse response) {
        //查询用户
        User user = userService.getUserInfo(username, password);

        //查询失败
        if (user == null) {
            return Result.fail("用户名或密码错误");
        }

        //成功
        long currentTime = System.currentTimeMillis();  //当前系统时间
        String token = tokenUtil.createJWT(user.getId(), user.getUsername(), currentTime);  //签发Token
        redisUtil.set(user.getUsername(), currentTime, tokenUtil.refresh_expire_time, TimeUnit.MILLISECONDS);
        response.setHeader("Authorization", token);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        Claims claimsByToken = tokenUtil.getClaimsByToken(token);
        System.out.println(claimsByToken);//{sub=张三, iat=1611574019, exp=1611574319, id=1, user=张三}
        return Result.success(user);
    }
}
