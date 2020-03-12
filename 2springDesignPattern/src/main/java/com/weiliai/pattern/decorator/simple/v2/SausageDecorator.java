package com.weiliai.pattern.decorator.simple.v2;

/**
 * @Author: Doug Li
 * @Date 2020/3/12
 * @Describe: 香肠装饰着类
 */
public class SausageDecorator extends BatterCakeDecorator {

    public SausageDecorator(BatterCake batterCake) {
        super(batterCake);
    }

    @Override
    protected String getMsg() {
        return super.getMsg()+"+1个根肠";
    }

    @Override
    protected int getPrice() {
        return super.getPrice()+2;
    }
}
