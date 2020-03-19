package com.songbo.dicshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DsAuctionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ds_auction_info_id", type = IdType.AUTO)
    private Integer dsAuctionInfoId;

    private Integer dsAuctionInfoUserId;

    private Double dsAuctionInfoPrice;

    private String dsAuctionInfoTime;

    private Integer dsAuctionInfoAuctionId;

    public DsAuctionInfo() {
    }

    public DsAuctionInfo(Integer dsAuctionInfoUserId, Double dsAuctionInfoPrice, String dsAuctionInfoTime, Integer dsAuctionInfoAuctionId) {
        this.dsAuctionInfoUserId = dsAuctionInfoUserId;
        this.dsAuctionInfoPrice = dsAuctionInfoPrice;
        this.dsAuctionInfoTime = dsAuctionInfoTime;
        this.dsAuctionInfoAuctionId = dsAuctionInfoAuctionId;
    }


}
