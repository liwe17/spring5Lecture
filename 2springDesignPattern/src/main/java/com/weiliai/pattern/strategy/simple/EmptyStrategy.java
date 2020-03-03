package com.weiliai.pattern.strategy.simple;

/**
 * @Author: Doug Li
 * @Date 2020/3/3
 * @Describe: 无促销
 */
public class EmptyStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("无促销活动");
    }
}
