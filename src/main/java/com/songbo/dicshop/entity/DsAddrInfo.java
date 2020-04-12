package com.songbo.dicshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 充值历史
 * </p>
 *
 * @author songbo
 * @since 2020-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DsAddrInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ds_addr_info_id", type = IdType.AUTO)
    private Integer dsAddrInfoId;

    private String dsAddrInfoTxid;

    private Double dsAddrInfoAmount;

    private Double dsAddrInfoIntegral;
    //0等候,1确认中,2已确认,3发送,4部分支付,5完成,6失败了,7已退还,8已过期
    private Integer dsAddrInfoStatus;
    //类别，1usdt
    private Integer dsAddrInfoCate;

    private Integer dsAddrInfoUserId;

    private String dsAddrInfoCreateTime;

    private String dsAddrInfoUpdateTime;


    public DsAddrInfo() {
    }

    public DsAddrInfo(String dsAddrInfoTxid, Double dsAddrInfoAmount, Double dsAddrInfoIntegral, Integer dsAddrInfoStatus, Integer dsAddrInfoCate, Integer dsAddrInfoUserId, String dsAddrInfoCreateTime, String dsAddrInfoUpdateTime) {
        this.dsAddrInfoTxid = dsAddrInfoTxid;
        this.dsAddrInfoAmount = dsAddrInfoAmount;
        this.dsAddrInfoIntegral = dsAddrInfoIntegral;
        this.dsAddrInfoStatus = dsAddrInfoStatus;
        this.dsAddrInfoCate = dsAddrInfoCate;
        this.dsAddrInfoUserId = dsAddrInfoUserId;
        this.dsAddrInfoCreateTime = dsAddrInfoCreateTime;
        this.dsAddrInfoUpdateTime = dsAddrInfoUpdateTime;
    }
}
