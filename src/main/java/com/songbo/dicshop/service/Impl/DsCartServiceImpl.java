package com.songbo.dicshop.service.Impl;

import com.songbo.dicshop.entity.DsCart;
import com.songbo.dicshop.service.DsCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName DsCartServiceImpl
 * @Description TODO
 * @Author songbo
 * @Date 2020/2/13 下午10:01
 **/
@Service
@Slf4j
public class DsCartServiceImpl implements DsCartService {
    @Override
    public boolean savecart(DsCart dsCart) {
        return false;
    }

    @Override
    public boolean updatecarts(String userId) {
        return false;
    }
}
