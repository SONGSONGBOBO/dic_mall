package com.songbo.dicshop.service.Impl;

import com.songbo.dicshop.entity.DsGoods;
import com.songbo.dicshop.service.DsGoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DsGoodsServiceImplTest {

    @Autowired
    private DsGoodsService dsGoodsService;

    @Test
    void getDoodsById() {
    }

    @Test
    void insertGoods() {
    }

    @Test
    @Transactional
    void updateDoods() {
        DsGoods dsGoods =
                new DsGoods("testupdate", "[\"asdas\", \"asdsad\"]", "[\"asdas\", \"asdsad\"]", 1);
        dsGoods.setDsGoodsId(1);
        System.out.println(dsGoodsService.updateGoods(dsGoods));
    }

    @Test
    @Transactional
    void delete() {
        System.out.println(dsGoodsService.deleteGoods(2));
    }
}