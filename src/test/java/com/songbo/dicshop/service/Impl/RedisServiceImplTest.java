package com.songbo.dicshop.service.Impl;

import com.songbo.dicshop.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisServiceImplTest {

    @Autowired
    private RedisService redisService;

    @Test
    void setValue() {
    }

    @Test
    void getValue() {
        Long haha = (Long)redisService.getValue("haha");
        System.out.println(haha);
    }

    @Test
    void setZset() {
    }

    @Test
    void getZset() {
    }

    @Test
    void setExpire() {
    }
}