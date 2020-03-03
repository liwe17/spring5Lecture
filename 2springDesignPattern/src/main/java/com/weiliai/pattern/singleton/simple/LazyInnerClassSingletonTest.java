package com.weiliai.pattern.singleton.simple;

import java.lang.reflect.Constructor;

/**
 * @Author: Doug Li
 * @Date 2020/2/23
 * @Describe: 内部类单例测试
 */
public class LazyInnerClassSingletonTest {

    public static void main(String[] args) throws Exception {
        test();

    }

    //反射破坏单例
    public static void test() throws Exception{
        //破坏单例
        final Class<LazyInnerClassSingleton> clazz = LazyInnerClassSingleton.class;

        //通过反射获取私有构造方法
        final Constructor<LazyInnerClassSingleton> c = clazz.getDeclaredConstructor(null);
        //开启强制访问
        c.setAccessible(true);

        //暴力初始化
        final LazyInnerClassSingleton instance = c.newInstance();

        //再次调用
        final LazyInnerClassSingleton instance2 = c.newInstance();

        System.out.println(instance==instance2);
    }

}
