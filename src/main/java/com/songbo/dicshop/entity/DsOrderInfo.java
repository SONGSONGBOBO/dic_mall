package com.songbo.dicshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ds_order_info")
public class DsOrderInfo {

    @TableId(value = "ds_order_info_id", type = IdType.AUTO)
    private Integer dsOrderInfoId;

    @TableField("ds_order_info_order_id")
    private Integer dsOrderInfoOrderId;

    @TableField("ds_order_info_num")
    private Integer dsOrderInfoNum;

    @TableField("ds_order_info_goods_id")
    private Integer dsOrderInfoGoodsId;

}