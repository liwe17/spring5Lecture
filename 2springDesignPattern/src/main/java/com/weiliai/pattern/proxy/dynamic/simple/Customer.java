package com.weiliai.pattern.proxy.dynamic.simple;

/**
 * @Author: Doug Li
 * @Date 2020/2/28
 * @Describe: TODO
 */
public class Customer implements Person {

    @Override
    public void findLove() {
        System.out.println("高富帅");
        System.out.println("身高180cm");
        System.out.println("有6块腹肌");
    }
}
