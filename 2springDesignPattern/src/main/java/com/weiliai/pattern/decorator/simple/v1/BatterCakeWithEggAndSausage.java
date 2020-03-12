package com.weiliai.pattern.decorator.simple.v1;

/**
 * @Author: Doug Li
 * @Date 2020/3/12
 * @Describe: TODO
 */
public class BatterCakeWithEggAndSausage extends BatterCakeWithEgg {

    @Override
    protected String getMsg() {
        return super.getMsg()+"+1根香肠";
    }

    //加1根香肠加2元
    @Override
    public int getPrice() {
        return super.getPrice()+2;
    }
}
