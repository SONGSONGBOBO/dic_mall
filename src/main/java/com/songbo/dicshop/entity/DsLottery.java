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
 * @since 2020-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("lottery实体")
public class DsLottery implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ds_lottery_id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer dsLotteryId;
    @ApiModelProperty("name")
    private String dsLotteryName;
    @ApiModelProperty("开始时间戳")
    private String dsLotteryStart;
    @ApiModelProperty("结束时间戳")
    private String dsLotteryEnd;
    @ApiModelProperty("奖项map")
    private String dsLotteryRewards;
    @ApiModelProperty("奖券单价")
    private Double dsLotteryPrice;

    /**
     * 结算奖励形式,1为usdt
     */
    @ApiModelProperty("lottery奖品种类，1为usdt，2为btc，3为eth")
    private Integer dsLotteryCate;
    @ApiModelProperty("奖票数量")
    private Integer dsLotteryTotal;
    @ApiModelProperty(hidden = true)
    private Integer dsLotterySale;
    @ApiModelProperty("1为已开奖，0为未开奖")
    private Integer dsLotteryStatus;


}
