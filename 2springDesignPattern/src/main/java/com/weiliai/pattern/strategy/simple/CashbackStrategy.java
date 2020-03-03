package com.weiliai.pattern.strategy.simple;

/**
 * @Author: Doug Li
 * @Date 2020/3/3
 * @Describe: 返现促销策略
 */
public class CashbackStrategy implements PromotionStrategy{

    @Override
    public void doPromotion() {
        System.out.println("返现促销,返现的金额转到支付宝账号");
    }
}
