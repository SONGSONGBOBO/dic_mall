package com.songbo.dicshop.bean.payments;

import lombok.Data;

/**
 * @ClassName EstimatedPriceResult
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/23 下午3:11
 **/
@Data
public class EstimatedPriceResult {

    private String currencyFrom;
    private String amountFrom;
    private String currencyTo;
    private String estimatedAmount;

    public EstimatedPriceResult() {
    }

    public EstimatedPriceResult(String currencyFrom, String amountFrom, String currencyTo, String estimatedAmount) {
        this.currencyFrom = currencyFrom;
        this.amountFrom = amountFrom;
        this.currencyTo = currencyTo;
        this.estimatedAmount = estimatedAmount;
    }
}
