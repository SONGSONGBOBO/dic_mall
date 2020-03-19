package com.songbo.dicshop.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("ds_goods_info")
@ApiModel(value = "子商品", description = "DsGoods")
public class DsGoodsInfo {

    @TableId(value = "ds_goods_info_id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long dsGoodsInfoId;
    /*
    * color size and more*/
    @TableField("ds_goods_info_name")
    @ApiModelProperty("name")
    private String dsGoodsInfoName;

    @TableField("ds_goods_info_price")
    @ApiModelProperty("实际价格")
    private Double dsGoodsInfoPrice;

    /*price of auction*/
    @TableField("ds_goods_info_auction")
    @ApiModelProperty("拍卖")
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