package com.weiliai.pattern.strategy.simple;

/**
 * @Author: Doug Li
 * @Date 2020/3/3
 * @Describe: 优惠券抵扣策略
 */
public class CouponStrategy implements PromotionStrategy {


    @Override
    public void doPromotion() {
        System.out.println("领取优惠券,课程的价格直接减优惠券面值抵扣");
    }
}
