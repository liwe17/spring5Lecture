package com.weiliai.pattern.strategy.pay;

/**
 * @Author: Doug Li
 * @Date 2020/3/3
 * @Describe: TODO
 */
public class UnionPay extends Payment {

    @Override
    public String getName() {
        return "银联支付";
    }

    @Override
    protected double queryBalance(String uid) {
        return 120;
    }
}
