package com.example.epi.shiro;

import com.example.epi.constants.Constant;
import com.example.epi.util.JwtUtil;
import com.example.epi.util.RedisUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class JWTFilter extends BasicHttpAuthenticationFilter {

    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;

    @Autowired
    public JWTFilter(JwtUtil jwtUtil, RedisUtil redisUtil) {
        this.jwtUtil = jwtUtil;
        this.redisUtil = redisUtil;
    }

    /**
     * 是否允许访问
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        System.out.println("-----------isAccessAllowed-------------");
        if (this.isLoginAttempt(request, response)) {
            //登录请求,header中有token信息，且未过期
            try {
                System.out.println("----------this.executeLogin(request, response)---------------");
                return this.executeLogin(request, response);
            } catch (Exception e) {
                System.out.println("----isAccessAllowed------失败");
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含token字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        System.out.println("------------isLoginAttempt------------");
        String accessToken = this.getAuthzHeader(request);
        System.out.println(accessToken);
        //如果accessToken不为空，且token验证为true，则需要登录
        if (accessToken != null && !accessToken.isEmpty()) {
            System.out.println("需要登录");
            Claims claims = jwtUtil.getClaimsFromToken(accessToken);
            System.out.println("-------------------------------------------"+claims);
            if (claims != null && !claims.isEmpty()) {
                //获取accessToken中的userId，与预先设置常量组合形成 key
                //claims.get("i")      返回值：null
                Integer userId = (Integer) claims.get("id");
                if (userId != null) {
                    String key = Constant.JWT_REFRESH_KEY + userId;
                    //校验令牌，claims非空，判断token是否过期，若未过期
                    if (!jwtUtil.isTokenExpired(claims)) {
                        //判断redis数据库中是否存 key 对应的缓存信息，若不存在则返回false

                        if (isRefresh(key, accessToken)) {
                            //刷新token并返回true，待完成
                            //----------刷新-------------
                            refreshTokens(accessToken, key, (HttpServletResponse) response);
                            return true;
                        }
                    } else {
                        //token过期，从redis中获取缓存token，进行判断，若redisToken未过期，则对token及redisToken进行刷新，刷新成功返回true
                        if (isRefresh(key, accessToken)) {
                            System.out.println("这是刷新token的方法");
                            refreshTokens(accessToken, key, (HttpServletResponse) response);
                            return true;
                        }
                    }
                }
            }
        } else {
            System.out.println("------token为空，返回login页面");
        }
        return false;
    }

    /**
     * 判断redis中是否存在该 key值，若存在，判断其是否过期，若未过期，且与 token信息相同，则需要刷新 token信息
     */
    private boolean isRefresh(String key, String token) {
        if (redisUtil.hasKey(key)) {
            if (redisUtil.getExpire(key, TimeUnit.MILLISECONDS) > 0) {
                return token.equals(redisUtil.get(key));
            }
        }
        return false;
    }

    /**
     * 刷新 jwtToken 和 refreshToken
     */
    private void refreshTokens(String token, String key, HttpServletResponse response) {
        //访问成功后 refreshToken 出现在响应头中
        String refreshToken = jwtUtil.refreshToken(token, null);
        response.setHeader(Constant.ACCESS_TOKEN, refreshToken);
        response.setHeader("Access-Control-Expose-Headers", Constant.ACCESS_TOKEN);

        //刷新 refreshToken
        redisUtil.set(key, refreshToken, jwtUtil.refresh_expire_time, TimeUnit.MILLISECONDS);
    }

    /**
     * 执行登录
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("--------executeLogin-------------");
        AuthenticationToken token = this.createToken(request, response);
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken must be created in order to execute a login attempt.";
            System.out.println(msg);
            throw new IllegalStateException(msg);
        } else {
            try {
                System.out.println("------subject--------");
                Subject subject = this.getSubject(request, response);
                subject.login(token);
                return this.onLoginSuccess(token, subject, request, response);
            } catch (AuthenticationException var5) {
                System.out.println("--------var5---------");
                return this.onLoginFailure(token, var5, request, response);
            }
        }
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        System.out.println("------------createToken-----------");
        String jwtToken = this.getAuthzHeader(request);
        return new JWTToken(jwtToken);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("-----------onLoginSuccess---------");
        //String successUrl = "/success";
        System.out.println("2222");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        System.out.println("request.getContextPath:----------"+httpServletRequest.getContextPath());
        System.out.println("request.getRequestURI:----------"+httpServletRequest.getRequestURI());
        //WebUtils.issueRedirect(request,response,successUrl);
        System.out.println("1111");
        return true;
    }

    /**
     * isAccessAllowed方法返回false时执行此方法
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        System.out.println("-------------onAccessDenied--------------");
        //未登录,重定向回登录页面
        String successUrl = "/";
        try {
            System.out.println("重定向");
            WebUtils.issueRedirect(request,response,successUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 对跨域提供支持
     */
    /*@Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        // 跨域已经在OriginFilter处全局配置
        *//*HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }*//*
        return super.preHandle(request, response);
    }*/
}
