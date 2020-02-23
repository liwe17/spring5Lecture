package com.weiliai.pattern.sp.simple;

/**
 * @Author: Doug Li
 * @Date 2020/2/23
 * @Describe: 内部类实现单例
 *  完美解决饿汉式单例模式内存浪费问题和synchronized的性能问题
 */
public class LazyInnerClassSingleton {

    //私有化构造函数
    private LazyInnerClassSingleton(){
        if(LazyHolder.LAZY !=null) //防止反射创建
        throw new RuntimeException("不允许初始化多个实例");
    }

    //使用LazyInnerClassSingleton默认先初始化内部类,如果不使用则不被加载
    public static LazyInnerClassSingleton getInstance(){
        return LazyHolder.LAZY;
    }

    //默认不加载
    private static class LazyHolder{
        private static final LazyInnerClassSingleton LAZY = new LazyInnerClassSingleton();
    }


}
