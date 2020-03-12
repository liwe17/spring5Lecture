package com.weiliai.pattern.decorator.simple.v2;

/**
 * @Author: Doug Li
 * @Date 2020/3/12
 * @Describe: 扩展套餐的抽象装饰者类
 */
public abstract class BatterCakeDecorator extends BatterCake {

    //静态代理,委派
    private BatterCake batterCake;

    public BatterCakeDecorator(BatterCake batterCake) {
        this.batterCake = batterCake;
    }

    @Override
    protected String getMsg() {
        return this.batterCake.getMsg();
    }

    @Override
    protected int getPrice() {
        return this.batterCake.getPrice();
    }
}
