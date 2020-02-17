package com.songbo.dicshop.service.Impl;

import com.songbo.dicshop.entity.DsGoodsInfo;
import com.songbo.dicshop.mapper.DsGoodsInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DsGoodsInfoServiceImplTest {

    @Resource
    private DsGoodsInfoMapper dsGoodsInfoMapper;
    @Test
    void contextLoads() {
        List<DsGoodsInfo> dsGoodsInfosByPid = dsGoodsInfoMapper.getDsGoodsInfosByPid(1);
        System.out.println(dsGoodsInfosByPid);
        System.out.println((int) (System.currentTimeMillis()/1000000-1578000));
    }

}