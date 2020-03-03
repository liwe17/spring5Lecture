package com.weiliai.pattern.proxy.stic.simple;

/**
 * @Author: Doug Li
 * @Date 2020/2/26
 * @Describe: TODO
 */
public class Son implements Person {

    @Override
    public void findLove() {
        //我没有时间,工作忙
        System.out.println("貌美肤白大长腿");
    }
}
