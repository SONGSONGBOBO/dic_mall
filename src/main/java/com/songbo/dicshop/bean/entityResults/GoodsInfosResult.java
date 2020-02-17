package com.songbo.dicshop.bean.entityResults;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.songbo.dicshop.entity.DsGoods;
import com.songbo.dicshop.entity.DsGoodsInfo;
import lombok.Data;

import java.util.List;

/**
 * @ClassName GoodsResult
 * @Description TODO
 * @Author songbo
 * @Date 2020/1/14 上午11:40
 **/
@Data
public class GoodsInfosResult {

    private DsGoods dsGoods;
    private List<DsGoodsInfo> goodsInfoList;
    private int sales;
    private int nums;
    private int views;

    public GoodsInfosResult(){}

    public GoodsInfosResult(DsGoods dsGoods, List<DsGoodsInfo> goodsInfoList, int sales, int nums, int views) {
        this.dsGoods = dsGoods;
        this.goodsInfoList = goodsInfoList;
        this.sales = sales;
        this.nums = nums;
        this.views = views;
    }
}
