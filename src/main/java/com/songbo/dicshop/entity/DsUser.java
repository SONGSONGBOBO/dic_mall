package com.songbo.dicshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("ds_user")
public class DsUser {

    @TableId(value = "ds_user_id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer dsUserId;

    @TableField("ds_user_name")
    @ApiModelProperty(value = "用户名",name = "dsUserName")
    private String dsUserName;

    @TableField("ds_user_tel")
    @ApiModelProperty(value = "用户电话",name = "dsUserTel")
    private String dsUserTel;

    @TableField("ds_user_pwd")
    @ApiModelProperty(value = "密码",name = "dsUserPwd")
    private String dsUserPwd;

    @TableField("ds_user_status")
    @ApiModelProperty(value = "状态",name = "dsUserStatus")
    private Integer dsUserStatus;

    @TableField("ds_user_level")
    @ApiModelProperty(hidden = true)
    private Integer dsUserLevel;

    @TableField("ds_user_invite")
    @ApiModelProperty(hidden = true)
    private String dsUserInvite;

    @TableField("ds_user_code")
    @ApiModelProperty(hidden = true)
    private String dsUserCode;

    @TableField("ds_user_mail")
    @ApiModelProperty(hidden = true)
    private String dsUserMail;


    public DsUser(){}

    public DsUser(String dsUserName, String dsUserTel, String dsUserPwd, Integer dsUserStatus, Integer dsUserLevel, String dsUserInvite, String dsUserCode, String dsUserMail) {
        this.dsUserName = dsUserName;
        this.dsUserTel = dsUserTel;
        this.dsUserPwd = dsUserPwd;
        this.dsUserStatus = dsUserStatus;
        this.dsUserLevel = dsUserLevel;
        this.dsUserInvite = dsUserInvite;
        this.dsUserCode = dsUserCode;
        this.dsUserMail = dsUserMail;
    }
}