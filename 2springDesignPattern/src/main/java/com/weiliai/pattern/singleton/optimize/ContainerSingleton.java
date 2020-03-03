package com.weiliai.pattern.singleton.optimize;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Doug Li
 * @Date 2020/2/23
 * @Describe: 容器式单例.适用于实例非常多的情况,便于管理,非线程安全(不太懂,自认为线程安全)
 */
public class ContainerSingleton {

    private ContainerSingleton(){};

    private static Map<String,Object> ioc = new HashMap<String,Object>();

    public static Object getBean(String className){
        synchronized (ioc){
            if(!ioc.containsKey(className)){
                Object obj = null;
                try{
                    obj = Class.forName(className).newInstance();
                    ioc.put(className,obj);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return obj;
            }else{
                return ioc.get(className);
            }
        }
    }
}
