package com.songbo.dicshop.enums;

public enum OrderEnum {
    //0等候,1确认中,2已确认,3发送,4部分支付,5完成,6失败了,7已退还,8已过期
    waiting(0)
    ,confirming(1)
     ,       confirmed(2)
    ,sending(3)
    ,partially_paid(4)
     ,       finished(5)
    ,failed(6)
     ,       refunded(7)
    ,expired(8);

    private OrderEnum(){

    }
    private int value;
    private OrderEnum(int value){
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
