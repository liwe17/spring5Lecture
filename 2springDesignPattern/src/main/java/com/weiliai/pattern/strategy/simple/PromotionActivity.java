package com.weiliai.pattern.strategy.simple;

import java.util.Objects;

/**
 * @Author: Doug Li
 * @Date 2020/3/3
 * @Describe: 促销活动方案
 * @see PromotionStrategyFactory 生产中基本使用方法
 */
public class PromotionActivity {

    private PromotionStrategy promotionStrategy;

    public PromotionActivity(PromotionStrategy promotionStrategy) {
        this.promotionStrategy = promotionStrategy;
    }

    public void execute(){
        promotionStrategy.doPromotion();
    }

    public static void main(String[] args) {

    }

    //使用方法1,只是测试
    public static void test(){
        final PromotionActivity activity = new PromotionActivity(new CouponStrategy());
        final PromotionActivity activity2 = new PromotionActivity(new CashbackStrategy());
        activity.execute();
        activity2.execute();
    }

    //一般只会参加一种优惠活动
    public static void test1(String strategy){
        if(Objects.equals("优惠券",strategy)){
            new PromotionActivity(new CouponStrategy()).execute();
        }else if(Objects.equals("返现",strategy)){
            new PromotionActivity(new CashbackStrategy());
        }else{
            System.err.println("未知的策略");
        }
    }

    //生产中一般活动越来越多,可以通过工厂模式和单例模式进行优化
}
