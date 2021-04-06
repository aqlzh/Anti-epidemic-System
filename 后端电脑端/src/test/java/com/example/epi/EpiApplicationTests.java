package com.example.epi;

import com.example.epi.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EpiApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testRedis() {
        redisUtil.set("redisTest", "redis test");
        System.out.println(redisUtil.get("redisTest"));
    }
}
