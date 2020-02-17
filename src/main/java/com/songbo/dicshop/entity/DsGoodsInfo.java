package com.songbo.dicshop.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ds_goods_info")
public class DsGoodsInfo {

    @TableId("ds_goods_info_id")
    private Integer dsGoodsInfoId;
    /*
    * color size and more*/
    @TableField("ds_goods_info_name")
    private String dsGoodsInfoName;

    @TableField("ds_goods_info_price")
    private Double dsGoodsInfoPrice;

    /*price of auction*/
    @TableField("ds_goods_info_auction")
    private Double dsGoodsInfoAuction;
    @TableField("ds_goods_info_num")
    private Integer dsGoodsInfoNum;
    @TableField("ds_goods_info_init")
    private Integer dsGoodsInfoInit;
    @TableField("ds_goods_info_pid")
    private Integer dsGoodsInfoPid;

    public DsGoodsInfo(){}

    public DsGoodsInfo(String dsGoodsInfoName, Double dsGoodsInfoPrice, Double dsGoodsInfoAuction, Integer dsGoodsInfoNum, Integer dsGoodsInfoInit, Integer dsGoodsInfoPid) {
        this.dsGoodsInfoName = dsGoodsInfoName;
        this.dsGoodsInfoPrice = dsGoodsInfoPrice;
        this.dsGoodsInfoAuction = dsGoodsInfoAuction;
        this.dsGoodsInfoNum = dsGoodsInfoNum;
        this.dsGoodsInfoInit = dsGoodsInfoInit;
        this.dsGoodsInfoPid = dsGoodsInfoPid;
    }
}