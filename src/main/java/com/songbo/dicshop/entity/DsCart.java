package com.songbo.dicshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@TableName("ds_cart")
@Data
public class DsCart {
    @TableId(value = "ds_cart_id", type = IdType.AUTO)
    private Integer dsCartId;

    @TableField("ds_cart_num")
    private Integer dsCartNum;

    @TableField("ds_cart_price")
    private Double dsCartPrice;

    @TableField("ds_cart_status")
    private Integer dsCartStatus;

    @TableField( "ds_cart_time")
    private String  dsCartTime;

    @TableField("ds_cart_goods_id")
    private Integer dsCartGoodsId;

    @TableField("ds_cart_user_id")
    private Integer dsCartUserId;

    @TableField("ds_cart_order_id")
    private Integer dsCartOrderId;

    public DsCart(){}

    public DsCart(Integer dsCartNum, Double dsCartPrice, Integer dsCartStatus, String dsCartTime, Integer dsCartGoodsId, Integer dsCartUserId) {
        this.dsCartNum = dsCartNum;
        this.dsCartPrice = dsCartPrice;
        this.dsCartStatus = dsCartStatus;
        this.dsCartTime = dsCartTime;
        this.dsCartGoodsId = dsCartGoodsId;
        this.dsCartUserId = dsCartUserId;
    }
}