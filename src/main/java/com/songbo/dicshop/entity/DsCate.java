package com.songbo.dicshop.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@TableName("ds_cate")
@Data
public class DsCate {
    @TableId(value = "ds_cate_id")
    private Integer dsCateId;

    @TableField("ds_cate_num")
    private Integer dsCateNum;

    @TableField("ds_cate_status")
    private Integer dsCateStatus;

    @TableField( "ds_cate_time")
    private String  dsCateTime;

    @TableField("ds_cate_goods_id")
    private Integer dsCateGoodsId;

    @TableField("ds_cate_user_id")
    private Integer dsCateUserId;

    @TableField("ds_cate_order_id")
    private Integer dsCateOrderId;


}