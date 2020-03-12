package com.weiliai.pattern.decorator.simple.v2;

/**
 * @Author: Doug Li
 * @Date 2020/3/12
 * @Describe: 基本煎饼,基础套餐
 */
public class BaseBatterCake extends BatterCake {

    @Override
    protected String getMsg() {
        return "煎饼";
    }

    @Override
    protected int getPrice() {
        return 5;
    }
}
