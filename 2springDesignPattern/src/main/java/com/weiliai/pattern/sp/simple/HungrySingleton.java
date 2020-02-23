package com.weiliai.pattern.sp.simple;

/**
 * @Author: Doug Li
 * @Date 2020/2/23
 * @Describe: 懒汉式单例
 *  在类加载的时候立即初始化,并且创建单例对象,绝对线程安全,创建发生在线程调用前,绝对的线程安全
 */
public class HungrySingleton {

    //先静态,后动态
    //先属性,后方法
    //先上后下

    //第一种写法
    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance(){
        return instance;
    }

    //第二种写法.利用静态代码块机制
    private static final HungrySingleton instance2;

    static {
        instance2 = new HungrySingleton();
    }

    public static HungrySingleton getInstance2(){
        return instance2;
    }



}
