package com.songbo.dicshop.service.Impl;

import com.songbo.dicshop.entity.DsGoods;
import com.songbo.dicshop.mapper.DsGoodsMapper;
import com.songbo.dicshop.service.DsGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName DsGoodsServiceImpl
 * @Description TODO
 * @Author songbo
 * @Date 2020/1/14 下午12:13
 **/
@Service
@Slf4j
public class DsGoodsServiceImpl implements DsGoodsService {

    @Resource
    private DsGoodsMapper dsGoodsMapper;

    @Override
    public DsGoods getDoodsById(int id) {

        return dsGoodsMapper.getGoodsInfoById(id);
    }

    @Override
    public DsGoods getGoodsByName(String name) {
        return dsGoodsMapper.getGoodsByName(name);
    }

    @Override
    public boolean insertGoods(DsGoods dsGoods) {
        try {
            dsGoodsMapper.insert(dsGoods);
            return true;
        } catch (Exception e) {
            log.error("insert goods fail: {} ", e);
            return false;
        }

    }

    @Override
    public boolean updateGoods(DsGoods dsGoods) {
        try {
            dsGoodsMapper.updateById(dsGoods);
            return true;
        } catch (Exception e) {
            log.error("update goods fail: {} ", e);
            return false;
        }

    }

    @Override
    public boolean deleteGoods(int id) {
        try {
            dsGoodsMapper.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("delete goods fail: {} ", e);
            return false;
        }

    }
}
