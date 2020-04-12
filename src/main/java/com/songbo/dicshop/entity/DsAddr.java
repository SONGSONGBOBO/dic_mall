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
 * @since 2020-02-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("用户dic账户地址")
public class DsAddr implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ds_addr_id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer dsAddrId;
    @ApiModelProperty("地址")
    private String dsAddrAddr;
    @ApiModelProperty(hidden = true)
    private Double dsAddrAmount;
    @ApiModelProperty(hidden = true)
    private Double dsAddrBalance;
    @ApiModelProperty(hidden = true)
    private Double dsAddrCost;
    @ApiModelProperty(hidden = true)
    private Integer dsAddrUserId;

    public DsAddr() {
    }

    public DsAddr(String dsAddrAddr, Integer dsAddrUserId) {
        this.dsAddrAddr = dsAddrAddr;
        this.dsAddrUserId = dsAddrUserId;
    }

    public DsAddr(String dsAddrAddr, Double dsAddrBalance, Integer dsAddrUserId) {
        this.dsAddrAddr = dsAddrAddr;
        this.dsAddrBalance = dsAddrBalance;
        this.dsAddrUserId = dsAddrUserId;
    }
}
