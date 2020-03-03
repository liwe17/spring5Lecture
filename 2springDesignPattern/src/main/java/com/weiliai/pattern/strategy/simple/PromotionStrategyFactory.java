package com.weiliai.pattern.strategy.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Doug Li
 * @Date 2020/3/3
 * @Describe:
 */
public class PromotionStrategyFactory {

    private static final Map<String,PromotionStrategy> PROMOTION_STRATEGY_MAP = new HashMap<>();

    static {
        PROMOTION_STRATEGY_MAP.put(PromotionKey.COUPON,new CouponStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionKey.CASHBACK,new CashbackStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionKey.GROUPBUY,new GroupBuyStrategy());
    }

    public static final PromotionStrategy NON_PROMOTION = new EmptyStrategy();

    private PromotionStrategyFactory(){

    }

    private static PromotionStrategy getPromotionStrategy(String promotionKey){
        final PromotionStrategy strategy = PROMOTION_STRATEGY_MAP.get(promotionKey);
        return strategy==null?NON_PROMOTION:strategy;
    }

    private interface PromotionKey{
        String COUPON = "COUPON";
        String CASHBACK = "CASHBACK";
        String GROUPBUY = "GROUPBUY";
    }

    public static void main(String[] args) {
        new PromotionActivity(PromotionStrategyFactory.getPromotionStrategy(PromotionKey.COUPON)).execute();
    }

}
