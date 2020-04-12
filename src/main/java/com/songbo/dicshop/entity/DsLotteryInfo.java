package com.songbo.dicshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.DataOutput;
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
    @ApiModelProperty(hidden = true)
    private Integer dsLotteryInfoId;
    @ApiModelProperty("号码数组")
    private String dsLotteryInfoNumber;
    @ApiModelProperty(hidden = true)
    private Integer dsLotteryInfoUserId;
    @ApiModelProperty("奖票id")
    private Integer dsLotteryInfoLotteryId;
    @ApiModelProperty(hidden = true)
    private Double dsLotteryInfoPrice;
    @ApiModelProperty(hidden = true)
    private String dsLotteryInfoLotteryTime;
    @ApiModelProperty(hidden = true)
    private Integer dsLotteryInfoStatus;

    public DsLotteryInfo() {
    }

    public DsLotteryInfo(String dsLotteryInfoNumber, Integer dsLotteryInfoUserId, Integer dsLotteryInfoLotteryId, Double dsLotteryInfoPrice, String dsLotteryInfoLotteryTime, Integer dsLotteryInfoStatus) {
        this.dsLotteryInfoNumber = dsLotteryInfoNumber;
        this.dsLotteryInfoUserId = dsLotteryInfoUserId;
        this.dsLotteryInfoLotteryId = dsLotteryInfoLotteryId;
        this.dsLotteryInfoPrice = dsLotteryInfoPrice;
        this.dsLotteryInfoLotteryTime = dsLotteryInfoLotteryTime;
        this.dsLotteryInfoStatus = dsLotteryInfoStatus;
    }
}
