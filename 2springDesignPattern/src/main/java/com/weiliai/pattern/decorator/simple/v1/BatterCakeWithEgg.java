package com.weiliai.pattern.decorator.simple.v1;

/**
 * @Author: Doug Li
 * @Date 2020/3/12
 * @Describe: TODO
 */
public class BatterCakeWithEgg extends BatterCake{

    @Override
    protected String getMsg() {
        return super.getMsg()+"1 个鸡蛋";
    }

    //加一个鸡蛋加1元
    @Override
    public int getPrice() {
        return super.getPrice()+1;
    }
}
