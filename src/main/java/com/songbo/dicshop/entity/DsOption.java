package com.songbo.dicshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.omg.CORBA.INTERNAL;

/**
 * @ClassName DsOpntion
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/11 下午12:32
 **/

@Data
@TableName("ds_option")
@ApiModel("网站设置")
public class DsOption {

    @TableId(value = "ds_option_id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer dsOptionId;

    @ApiModelProperty(value = "轮播图")
    private String dsOptionImgs;

    @ApiModelProperty(value = "邀请奖励（老用户）")
    private Double dsOptionInviteRewards;
    @ApiModelProperty(value = "邀请奖励（新用户）")
    private Double dsOptionInviteIntegral;
    @ApiModelProperty(value = "注册奖励")
    private Double dsOptionRegisterIntegral;

    @ApiModelProperty(hidden = true)
    private Integer dsOptionStatus;
}
