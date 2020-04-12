package com.songbo.dicshop.service;


import com.songbo.dicshop.entity.DsGoods;

import java.util.List;

public interface DsGoodsService {

    DsGoods getDoodsById(int id);

    DsGoods getGoodsByName(String name);

    boolean insertGoods(DsGoods dsGoods);

    boolean updateGoods(DsGoods dsGoods);

    boolean deleteGoods(int id);

    List<DsGoods> getGoodsList();

    DsGoods getByAuctionInfoService(int auctionInfoId);

}
