package com.songbo.dicshop.service.Impl;

import com.songbo.dicshop.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import reactor.util.annotation.Nullable;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisServiceImpl
 * @Description TODO
 * @Author songbo
 * @Date 2020/2/21 下午9:56
 **/
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, Object> template;


    @Override
    public void setValue(String key, Object o, Duration duration) {
        if (duration != null) {
            template.opsForValue().set(key, o, duration);
        } else {
            template.opsForValue().set(key, o);
        }
    }

    @Override
    public Object getValue(String key) {
        return template.opsForValue().get(key);
    }

    @Override
    public Boolean setZset(String key, Object o, double source) {

        return template.opsForZSet().add(key, o, source);
    }

    @Override
    public <T> Set<T>  getZset(String key) {

        return (Set<T>)template.opsForZSet().range(key, 0, -1);
    }

    @Override
    public boolean setExpire(String key, Long time, TimeUnit timeUnit) {
        Boolean expire = template.expire(key, time, timeUnit);
        return  expire == null ? false : expire;
    }

    @Override
    public boolean deleteKey(String key) {
        try {
            return template.delete(key);
        } catch (Exception e) {
            log.error("redis delete key fail:{}", e);
            return false;
        }


    }


}
