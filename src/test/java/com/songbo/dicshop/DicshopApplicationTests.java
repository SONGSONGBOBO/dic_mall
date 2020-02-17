package com.songbo.dicshop;

import com.songbo.dicshop.entity.DsGoodsInfo;
import com.songbo.dicshop.mapper.DsGoodsInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class DicshopApplicationTests {

    @Resource
    private DsGoodsInfoMapper dsGoodsInfoMapper;
    @Test
    void contextLoads() {
        List<DsGoodsInfo> dsGoodsInfosByPid = dsGoodsInfoMapper.getDsGoodsInfosByPid(1);
        System.out.println(dsGoodsInfosByPid);

    }

}
