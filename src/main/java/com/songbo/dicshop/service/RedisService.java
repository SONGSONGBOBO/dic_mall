package com.songbo.dicshop.service;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public interface RedisService {
    /**
      *@Description TODO
      *@param
      *@return
    **/
    void setValue(String key, Object o, Duration duration);
    /**
      *@Description TODO
      *@param
      *@return
    **/
    Object getValue(String key);

    /**
     *@Description TODO zset
     *@return
     *
     * @param*/
    Boolean setZset(String key,  Object o, double source);
    /**
     *@Description TODO get all zset-value
     *@return
     *
     * @param*/
     <T> Set<T>  getZset(String key);
     /**
       *@Description TODO
       *@param
       *@return
     **/
     boolean setExpire(String key, Long time, TimeUnit timeUnit);
     /**
       *@Description TODO
       *@param
       *@return
     **/
     boolean deleteKey(String key);
}
