package com.weiliai.pattern.observer.guava;

import com.google.common.eventbus.Subscribe;

/**
 * @Author: Doug Li
 * @Date 2020/3/15
 * @Describe: TODO
 */
public class GuavaEvent {


    @Subscribe
    public void subscribe(String str){
        //业务逻辑
        System.out.println("执行subscribe方法,传入的参数是:"+str);
    }


}
