package com.songbo.dicshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "订单对象", description = "DsOrder")
@TableName("ds_order")
@Data
public class DsOrder {

    @ApiModelProperty(hidden = true)
    @TableId(value = "ds_order_id", type = IdType.AUTO)
    private Integer dsOrderId;

    @ApiModelProperty(hidden = true)
    @TableField("ds_order_price")
    private String dsOrderPrice;

    @ApiModelProperty(value = "订单创建时间",name = "dsOrderCreateTime")
    @TableField("ds_order_create_time")
    private String dsOrderCreateTime;

    @ApiModelProperty(value = "订单更新时间",name = "dsOrderUpdateTime")
    @TableField("ds_order_update_time")
    private String dsOrderUpdateTime;

    @ApiModelProperty(value = "订单状态（0：关闭，1：已创建，2：已完成）",name = "dsOrderStatus")
    @TableField("ds_order_status")
    private Integer dsOrderStatus;

    @ApiModelProperty(value = "用户id",name = "dsOrderUserId")
    @TableField("ds_order_user_id")
    private Integer dsOrderUserId;

    @ApiModelProperty(value = "用户下单所用具体信息id",name = "dsOrderUserInfoId")
    @TableField("ds_order_user_info_id")
    private Integer dsOrderUserInfoId;

    @ApiModelProperty(value = "选中购物车的id列表",name = "dsCartList")
    @TableField(exist = false)
    private List<String> dsCartListId;

}