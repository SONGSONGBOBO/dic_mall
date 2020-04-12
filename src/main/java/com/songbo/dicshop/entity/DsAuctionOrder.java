package com.songbo.dicshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("拍卖订单")
public class DsAuctionOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ds_auction_order_id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer dsAuctionOrderId;
    @ApiModelProperty(hidden = true)
    private Integer dsAuctionOrderNum;
    @ApiModelProperty("拍卖结算价格")
    private Double dsAuctionOrderAuction;
    @ApiModelProperty(hidden = true)
    private Integer dsAuctionOrderAuctionInfoId;
    @ApiModelProperty(hidden = true)
    private Integer dsAuctionOrderUserId;
    @ApiModelProperty("拍卖订单状态，0等候,1确认中,2已确认,3发送,4部分支付,5完成,6失败了,7已退还,8已过期")
    private Integer dsAuctionOrderStatus;
    @ApiModelProperty(hidden = true)
    private String dsAuctionOrderPaymentId;
    @ApiModelProperty(hidden = true)
    private String dsAuctionOrderCreateTime;
    @ApiModelProperty(hidden = true)
    private String dsAuctionOrderUpdateTime;
    @ApiModelProperty(hidden = true)
    private String dsAuctionOrderAddr;
    @ApiModelProperty(hidden = true)
    private Integer dsAuctionOrderLevel;

    public DsAuctionOrder() {}

    public DsAuctionOrder(Integer dsAuctionOrderNum, Double dsAuctionOrderAuction, Integer dsAuctionOrderAuctionInfoId, Integer dsAuctionOrderUserId, Integer dsAuctionOrderStatus, String dsAuctionOrderPaymentId, String dsAuctionOrderCreateTime, String dsAuctionOrderUpdateTime, String dsAuctionOrderAddr, Integer dsAuctionOrderLevel) {
        this.dsAuctionOrderNum = dsAuctionOrderNum;
        this.dsAuctionOrderAuction = dsAuctionOrderAuction;
        this.dsAuctionOrderAuctionInfoId = dsAuctionOrderAuctionInfoId;
        this.dsAuctionOrderUserId = dsAuctionOrderUserId;
        this.dsAuctionOrderStatus = dsAuctionOrderStatus;
        this.dsAuctionOrderPaymentId = dsAuctionOrderPaymentId;
        this.dsAuctionOrderCreateTime = dsAuctionOrderCreateTime;
        this.dsAuctionOrderUpdateTime = dsAuctionOrderUpdateTime;
        this.dsAuctionOrderAddr = dsAuctionOrderAddr;
        this.dsAuctionOrderLevel = dsAuctionOrderLevel;
    }
}