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
    @ApiModelProperty("开始时间戳")
    private String dsLotteryStart;
    @ApiModelProperty("结束时间戳")
    private String dsLotteryEnd;
    @ApiModelProperty("奖金等级数量，数组，[1,2,3]就是三个奖项，一等1人，以此类推")
    private String dsLotteryNum;
    @ApiModelProperty("奖券价格")
    private Double dsLotteryPrice;
    @ApiModelProperty("奖金,数组,[21.1,13.2,4]一等奖21.1以此类推")
    private String dsLotteryRewards;

    /**
     * 结算奖励形式,1为btc
     */
    @ApiModelProperty("lottery种类，1为btc，2为usdt，3为eth")
    private Integer dsLotteryCate;


}
