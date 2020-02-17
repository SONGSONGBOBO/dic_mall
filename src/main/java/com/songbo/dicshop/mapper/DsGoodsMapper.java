package com.songbo.dicshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.songbo.dicshop.entity.DsGoods;


public interface DsGoodsMapper extends BaseMapper<DsGoods> {
    DsGoods getGoodsInfoById(int id);

    DsGoods getGoodsByName(String name);
}