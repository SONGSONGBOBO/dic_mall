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
 * @since 2020-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("自动拍卖")
public class DsAuctionAuto implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ds_auction_auto_id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer dsAuctionAutoId;
    @ApiModelProperty("自动拍卖最高限度")
    private Double dsAuctionAutoMax;
    @ApiModelProperty("自动拍卖一次追加额度")
    private Double dsAuctionAutoAdd;
    @ApiModelProperty("更新时间")
    private String dsAuctionAutoTime;
    @ApiModelProperty("拍卖id")
    private Integer dsAuctionAutoAuctionId;
    @ApiModelProperty(hidden = true)
    private Integer dsAuctionAutoUserId;
    @ApiModelProperty(hidden = true)
    private Integer dsAuctionAutoStatus;


}
