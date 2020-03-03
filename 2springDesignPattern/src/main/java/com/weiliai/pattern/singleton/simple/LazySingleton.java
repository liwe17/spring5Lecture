package com.weiliai.pattern.singleton.simple;

/**
 * @Author: Doug Li
 * @Date 2020/2/23
 * @Describe: 懒加载
 */
public class LazySingleton {

    private LazySingleton() {
    }

    //静态快,公共内存区域
    private static LazySingleton instance;

    private static LazySingleton instance2;

    private volatile static LazySingleton instance3; //防止指令重排序,内存可见性

    //使用的时候创建,线程不安全
    public static LazySingleton getInstance(){
        if(instance==null){
            instance = new LazySingleton();
        }
        return instance;
    }

    //使用的时候创建,线程不安全
    public static synchronized LazySingleton getInstance2(){
        if(instance2==null){
            instance2 = new LazySingleton();
        }
        return instance;
    }

    //加到方法上,锁粒度太大
    public static  LazySingleton getInstance3(){
        if(instance3 == null){
            synchronized (LazySingleton.class){
                if(instance3 == null){
                    instance3 = new LazySingleton();
                    //1.分配内存给这个对象
                    //2.初始化对象
                    //3.设置instance3指向刚分配的内存地址
                }
            }
        }
        return instance3;
    }

}
