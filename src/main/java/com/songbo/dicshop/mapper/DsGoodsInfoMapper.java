package com.songbo.dicshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.songbo.dicshop.entity.DsGoodsInfo;

import java.util.List;


public interface DsGoodsInfoMapper extends BaseMapper<DsGoodsInfo> {

    List<DsGoodsInfo> getDsGoodsInfosByPid(int pid);

    DsGoodsInfo getDsGoodsInfoByName(String name);
}