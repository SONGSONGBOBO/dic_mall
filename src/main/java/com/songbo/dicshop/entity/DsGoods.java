package com.songbo.dicshop.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "商品对象", description = "DsGoods")
@TableName("ds_goods")
@Data
public class DsGoods {

    @ApiModelProperty("更新的时候传")
    @TableId(value = "ds_goods_id", type = IdType.AUTO)
    private Integer dsGoodsId;

    @ApiModelProperty(value = "商品名",name = "dsGoodsName")
    @TableField( "ds_goods_name")
    private String dsGoodsName;

    /**
     * 商品信息
     */
    @ApiModelProperty(value = "商品主图片",name = "dsGoodsInfo")
    @TableField( "ds_goods_info")
    private String dsGoodsInfo;

    /**
     * 商品详情
     */
    @ApiModelProperty(value = "副图",name = "dsGoodsMessage")
    @TableField("ds_goods_message")
    private String dsGoodsMessage;
    @ApiModelProperty(value = "卖点")
    @TableField("ds_goods_detail")
    private String dsGoodsDetail;

    @ApiModelProperty(hidden = true)
    @TableField("ds_goods_status")
    private Integer dsGoodsStatus;
    @ApiModelProperty(value = "商品价格，列表页面显示")
    @TableField("ds_goods_price")
    private double dsGoodsPrice;



    public DsGoods() {
    }

    public DsGoods(String dsGoodsName, String dsGoodsInfo, String dsGoodsMessage, String dsGoodsDetail, Integer dsGoodsStatus, double dsGoodsPrice) {
        this.dsGoodsName = dsGoodsName;
        this.dsGoodsInfo = dsGoodsInfo;
        this.dsGoodsMessage = dsGoodsMessage;
        this.dsGoodsDetail = dsGoodsDetail;
        this.dsGoodsStatus = dsGoodsStatus;
        this.dsGoodsPrice = dsGoodsPrice;
    }
}