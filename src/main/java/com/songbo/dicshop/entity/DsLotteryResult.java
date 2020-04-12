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
 * @ClassName DsLotteryResult
 * @Description TODO
 * @Author songbo
 * @Date 2020/4/9 下午4:22
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("lottery开奖信息")
public class DsLotteryResult implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "ds_lottery_Result_id", type = IdType.AUTO)
    private Integer dsLotteryResultId;
    @ApiModelProperty("hash")
    private String dsLotteryResultHash;
    @ApiModelProperty("开奖结果")
    private String dsLotteryResultNumber;
    @ApiModelProperty(hidden = true)
    private Integer dsLotteryResultLotterId;

    public DsLotteryResult(String dsLotteryResultHash, String dsLotteryResultNumber, Integer dsLotteryResultLotterId) {
        this.dsLotteryResultHash = dsLotteryResultHash;
        this.dsLotteryResultNumber = dsLotteryResultNumber;
        this.dsLotteryResultLotterId = dsLotteryResultLotterId;
    }

    public DsLotteryResult() {
    }
}
