package com.songbo.dicshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@TableName("ds_user_info")
@ApiModel(value = "userinfo实体", description = "DsUserInfo")
public class DsUserInfo {

    @TableId(value = "ds_user_info_id", type = IdType.AUTO)
    @ApiModelProperty("更新的时候传id，其他不要传")
    private Integer dsUserInfoId;

    @TableField("ds_user_info_tel")
    @ApiModelProperty("电话号")
    private String dsUserInfoTel;

    @TableField("ds_user_info_addr")
    @ApiModelProperty("地址")
    private String dsUserInfoAddr;

    @TableField("ds_user_info_cate")
    @ApiModelProperty("地址种类，0为实际地址，1为btc地址，2为usdt地址，3为eth地址")
    private Integer dsUserInfoCate;

    @TableField("ds_user_info_user_id")
    @ApiModelProperty(hidden = true)
    private Integer dsUserInfoUserId;



}