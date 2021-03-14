package com.example.epi.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class JwtUtil {
    @Value("${token.expire-time}")
    public long expire_time;

    @Value("${token.refresh-expire-time}")
    public long refresh_expire_time;

    @Value("${token.refresh-expire-app-time}")
    public long refresh_expire_app_time;

    @Value("${token.token-secret}")
    private String secret;

    @Value("${token.issuer}")
    private String issuer;

    public String getAccessToken(String subject, Map<String, Object> claims) {
        return generateToken(issuer, subject, claims, expire_time, secret);
    }

    /**
     * 生成令牌
     * @return 返回生成的令牌
     */
    public String generateToken(String issuer, String subject, Map<String,Object> claims, long ttlMillis, String secretKey) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        try {
            JwtBuilder builder = Jwts.builder()
                    .setHeaderParam("typ", "JWT")
                    .setSubject(subject)
                    .setIssuedAt(now)
                    .signWith(signatureAlgorithm, secretKey);
            if (null != claims) {
                builder.setClaims(claims);
            }
            if (!(issuer == null || issuer.isEmpty())) {
                builder.setIssuer(issuer);
            }
            if (ttlMillis >= 0) {
                long expMillis = nowMillis + ttlMillis;
                Date exp = new Date(expMillis);
                builder.setExpiration(exp);
            }
            return builder.compact();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 生产 PC refresh_token
     */
    public String getRefreshToken(String subject, Map<String,Object> claims) {
        return generateToken(issuer, subject, claims, refresh_expire_time, secret);
    }

    /**
     * 生产 App端 refresh_token
     */
    public String getRefreshAppToken(String subject, Map<String,Object> claims) {
        return generateToken(issuer, subject, claims, refresh_expire_app_time, secret);
    }

    /**
     * 从令牌中获取数据声明
     */
    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
        }
        return claims;
    }

    /**
     * 获取用户id
     */
    public Integer getUserId(String token) {
        Integer userId = null;
        try {
            Claims claims = getClaimsFromToken(token);
            userId = (Integer) claims.get("id");
        } catch (Exception e) {
            log.error("error={}",e);
        }
        return userId;
    }

    /**
     * 获取用户名
     */
    public String getUserName(String token) {
        String username = null;
        try {
            Claims claims = getClaimsFromToken(token);
            username = (String) claims.get("user");
        } catch (Exception e) {
            log.error("error={}",e);
        }
        return username;
    }

    /**
     * 验证token 是否过期
     */
    public Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            log.error("error={}",e);
            return true;
        }
    }

    /**
     * 校验令牌
     */
    public Boolean validateToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return (null != claims && !isTokenExpired(token));
    }

    /**
     * 刷新token
     */
    public String refreshToken(String refreshToken, Map<String,Object> claims) {
        String refreshedToken;
        try {
            Claims parserClaims = getClaimsFromToken(refreshToken);
            //刷新token的时候如果参数claims为空说明原先的用户信息不变，所以就引用上个token里的内容
            if (null == claims) {
                claims = parserClaims;
            }
            refreshedToken = generateToken(parserClaims.getIssuer(), parserClaims.getSubject(),claims,expire_time,secret);
        } catch (Exception e) {
            refreshedToken = null;
            log.error("error={}",e);
        }
        return refreshedToken;
    }

    /**
     * 获取token的剩余过期时间
     */
    public long getRemainingTime(String token) {
        long result = 0;
        try {
            long nowMillis = System.currentTimeMillis();
            result = getClaimsFromToken(token).getExpiration().getTime() - nowMillis;
        } catch (Exception e) {
            log.error("error={}",e);
        }
        return result;
    }
}
