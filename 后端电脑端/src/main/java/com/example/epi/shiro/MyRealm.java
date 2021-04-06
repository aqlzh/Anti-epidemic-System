package com.example.epi.shiro;

import com.example.epi.util.JwtUtil;
import io.jsonwebtoken.Claims;
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
        //获取claims中的role
        String role = null;
        Claims claims = jwtUtil.getClaimsFromToken(token);
        if (claims != null && !claims.isEmpty()) {
            //claims 不为空时，获取角色 role
            role = (String) claims.get("role");
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(role);
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

        //获取用户id和用户名
        Claims claimsFromToken = jwtUtil.getClaimsFromToken(token);
        if (claimsFromToken != null && !claimsFromToken.isEmpty()) {
            //claimsFromToken 不为null
            Integer userId = (Integer) claimsFromToken.get("id");
            System.out.println("userId:-------"+userId);
            String userName = (String) claimsFromToken.get("user");
            System.out.println("userName------------:"+userName);

            if (userId == null || userName == null) {
                //令牌无效
                System.out.println("---------令牌无效---------");
                throw new AuthenticationException("token invalid");
            }
        }

        return new SimpleAuthenticationInfo(token, token, MyRealm.class.getName());
    }
}
