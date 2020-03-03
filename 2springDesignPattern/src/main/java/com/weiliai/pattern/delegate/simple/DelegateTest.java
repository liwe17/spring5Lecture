package com.weiliai.pattern.delegate.simple;

/**
 * @Author: Doug Li
 * @Date 2020/3/3
 * @Describe: TODO
 */
public class DelegateTest {

    public static void main(String[] args) {

        //代理模式注重的是过程,委派模式注重的是结果
        //策略模式主动可扩展性(外部扩展性),委派模式注重内部的灵活性和可复用性
        //委派模式的核心即使分发,调度,派遣,委派模式就是静态代理和策略模式的一种特殊组合
        new Boss().command("登录",new Leader());
    }
}
