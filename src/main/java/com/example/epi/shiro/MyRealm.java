package com.example.epi.shiro;

import com.example.epi.util.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyRealm extends AuthorizingRealm {
    private final JwtUtil jwtUtil;

    @Autowired
    public MyRealm(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("--------------doGetAuthorizationInfo----------------");
        String token = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("token-----------:"+token);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        System.out.println("--------------dad_______________");
        simpleAuthorizationInfo.addRole("admin");
        //查询数据库，获取权限信息
        return simpleAuthorizationInfo;
    }

    /**
     * 认证登录
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("------------doGetAuthenticationInfo----------------");
        JWTToken jwtToken = (JWTToken) authenticationToken;
        String token = (String) jwtToken.getPrincipal();
        System.out.println(token);

        //获取用户id和用户名
        Integer userId = jwtUtil.getUserId(token);
        System.out.println("userId:-------"+userId);
        String userName = jwtUtil.getUserName(token);
        System.out.println("userName------------:"+userName);
        if (userId == null || userName == null) {
            //令牌无效
            System.out.println("---------令牌无效---------");
            throw new AuthenticationException("token invalid");
        }
        if (!jwtUtil.validateToken(token)) {
            System.out.println("----------------用户名或密码错误-------------");
            throw new AuthenticationException("Username or password error");
        }
        return new SimpleAuthenticationInfo(token, token, MyRealm.class.getName());
    }
}
