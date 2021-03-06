package com.example.epi.util;

//import com.sun.org.slf4j.internal.Logger;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
//import sun.security.jgss.GSSToken;

import java.util.Date;


@Slf4j
@Component
public class TokenUtil {
    @Value("${token.expire-time}")
    public long expire_time;

    @Value("${token.refresh-expire-time}")
    public long refresh_expire_time;

    @Value("${token.token-secret}")
    private String secret;

    /**
     * 生成令牌
     * @param userId
     * @param userName
     * @param currentTime
     * @return
     */
    public String createJWT(Long userId, String userName, Long currentTime) {
        String token = null;
        Date currentDate = new Date(currentTime);
        Claims claims = Jwts.claims();
        claims.put("id", userId);
        claims.put("user", userName);
        try {
            //过期时间戳
            Date expireDate = new Date(currentTime + expire_time);
            token = Jwts.builder()
                    .setHeaderParam("typ", "JWT")
                    .setSubject(userName+"")
                    .setIssuedAt(currentDate)   //jwt签发时间
                    .setExpiration(expireDate)  //token过期时间
                    .addClaims(claims)
                    .signWith(SignatureAlgorithm.HS512, secret) //设置签名使用的签名算法和签名使用的秘钥
                    .compact();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return token;
        }
    }

    /**
     * 获取token中的信息
     * @param token
     * @return
     */
    public Claims getClaimsByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            Logger log = null;
            log.debug("validate is token error ", e);
            return null;
        }
    }

    /**
     * 判断令牌是否过期
     * @param token
     * @return
     */
    public Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsByToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /*public String refreshToken(String token) {
        try {
            Claims claims = getClaimsByToken(token);

        }
    }*/
}
