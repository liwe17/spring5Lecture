package com.weiliai.pattern.singleton.simple;

import com.weiliai.pattern.singleton.optimize.ThreadLocalSingleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Doug Li
 * @Date 2020/2/23
 * @Describe: TODO
 */
public class SingletonTest {

    public static final int num = 200;
    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(num);
        for (int i = 0; i < num; i++) {
            service.execute(new ExecutorThread());
        }
        service.shutdown();
        System.out.println("End");
    }

}
class ExecutorThread implements Runnable{

    @Override
    public void run() {
        //LazySingleton instance = LazySingleton.getInstance(); //线程不安全
        //LazySingleton instance = LazySingleton.getInstance2();
        //final Object instance = ContainerSingleton.getBean("com.weiliai.pattern.sp.simple.SingletonTest");
        final ThreadLocalSingleton instance = ThreadLocalSingleton.getInstance();
        System.out.println(Thread.currentThread().getName()+":"+instance);
    }
}
