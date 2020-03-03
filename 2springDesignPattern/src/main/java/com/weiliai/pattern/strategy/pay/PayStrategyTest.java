package com.weiliai.pattern.strategy.pay;

/**
 * @Author: Doug Li
 * @Date 2020/3/3
 * @Describe: TODO
 */
public class PayStrategyTest {

    public static void main(String[] args) {

        //省略把商品添加到购物车再从购物车下单,直接从订单开始
        final Order order = new Order("1", "2020020303", 324.25);

        //支付时候选择
        System.out.println(order.pay());

    }
}
