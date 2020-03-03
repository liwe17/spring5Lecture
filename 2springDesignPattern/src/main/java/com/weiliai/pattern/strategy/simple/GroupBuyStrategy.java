package com.weiliai.pattern.strategy.simple;

/**
 * @Author: Doug Li
 * @Date 2020/3/3
 * @Describe: 团购策略
 */
public class GroupBuyStrategy implements PromotionStrategy{

    @Override
    public void doPromotion() {
        System.out.println("拼团,满20人成团,全团享受优惠价");
    }
}
