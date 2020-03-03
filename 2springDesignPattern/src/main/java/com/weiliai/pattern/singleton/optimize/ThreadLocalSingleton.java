package com.weiliai.pattern.singleton.optimize;

/**
 * @Author: Doug Li
 * @Date 2020/2/23
 * @Describe: 单例
 */
public class ThreadLocalSingleton {

    private static final ThreadLocal<ThreadLocalSingleton> threadLocalInstance = new ThreadLocal<ThreadLocalSingleton>(){
        @Override
        protected ThreadLocalSingleton initialValue() {
            return new ThreadLocalSingleton();
        }
    };

    private ThreadLocalSingleton() {
    }

    public static ThreadLocalSingleton getInstance(){
        return threadLocalInstance.get();
    }
}
