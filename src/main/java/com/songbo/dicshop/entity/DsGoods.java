package com.songbo.dicshop.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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

    @ApiModelProperty(hidden = true)
    @TableId("ds_goods_id")
    private Integer dsGoodsId;

    @ApiModelProperty(value = "商品名",name = "dsGoodsName")
    @TableField( "ds_goods_name")
    private String dsGoodsName;

    /**
     * 商品信息
     */
    @ApiModelProperty(value = "商品图片信息",name = "dsGoodsInfo")
    @TableField( "ds_goods_info")
    private String dsGoodsInfo;

    /**
     * 商品详情
     */
    @ApiModelProperty(value = "商品详情、规格等",name = "dsGoodsMessage")
    @TableField("ds_goods_message")
    private String dsGoodsMessage;

    @ApiModelProperty(value = "商品状态（是否显示）",name = "dsGoodsStatus")
    @TableField("ds_goods_status")
    private Integer dsGoodsStatus;

    public DsGoods() {
    }

    public DsGoods(String dsGoodsName, String dsGoodsInfo, String dsGoodsMessage, Integer dsGoodsStatus) {
        this.dsGoodsName = dsGoodsName;
        this.dsGoodsInfo = dsGoodsInfo;
        this.dsGoodsMessage = dsGoodsMessage;
        this.dsGoodsStatus = dsGoodsStatus;
    }
}