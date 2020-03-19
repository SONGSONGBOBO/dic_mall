package com.songbo.dicshop.service;

import com.songbo.dicshop.entity.DsGoodsInfo;

import java.util.List;

public interface DsGoodsInfoService {

    List<DsGoodsInfo> getGoodsInfosByPid(int pid);

    boolean insertGoodsInfo(DsGoodsInfo dsGoodsInfo);

    DsGoodsInfo getInfoByName(String name);

    DsGoodsInfo getInfoById(int id);
}
