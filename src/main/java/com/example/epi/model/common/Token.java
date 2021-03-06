package com.example.epi.model.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component  //让spring容器可以扫描到这个类
@ConfigurationProperties(prefix = "token")
public class Token {

    private long expireTime;   //token到期时间
    private long refreshExpireTime;   //refreshtoken到期时间
    private String tokenSecret;    //密钥盐

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public long getRefreshExpireTime() {
        return refreshExpireTime;
    }

    public void setRefreshExpireTime(long refreshExpireTime) {
        this.refreshExpireTime = refreshExpireTime;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    @Override
    public String toString() {
        return "Token{" +
                "expireTime=" + expireTime +
                ", refreshExpireTime=" + refreshExpireTime +
                ", tokenSecret='" + tokenSecret + '\'' +
                '}';
    }
}
