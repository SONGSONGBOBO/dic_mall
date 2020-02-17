package com.songbo.dicshop.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("ds_user_info")
public class DsUserInfo {

    @TableId("ds_user_info_id")
    private Integer dsUserInfoId;

    @TableField("ds_user_info_tel")
    private String dsUserInfoTel;

    @TableField("ds_user_info_addr")
    private String dsUserInfoAddr;

    @TableField("ds_user_info_user_id")
    private Integer dsUserInfoUserId;

}