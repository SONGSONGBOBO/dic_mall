package com.songbo.dicshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author songbo
 * @since 2020-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("lottery购买记录实体")
public class DsLotteryInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ds_lottery_info_id", type = IdType.AUTO)
    private Integer dsLotteryInfoId;
    @ApiModelProperty(hidden = true)
    private String dsLotteryInfoTxid;
    @ApiModelProperty(hidden = true)
    private Integer dsLotteryInfoUserId;
    @ApiModelProperty("奖票id")
    private Integer dsLotteryInfoLotteryId;
    @ApiModelProperty(hidden = true)
    private Integer dsLotteryInfoLotteryLevel;
    @ApiModelProperty(hidden = true)
    private Integer dsLotteryInfoLotteryTime;



}
