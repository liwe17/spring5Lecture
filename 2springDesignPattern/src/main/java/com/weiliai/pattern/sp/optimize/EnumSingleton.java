package com.weiliai.pattern.sp.optimize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * @Author: Doug Li
 * @Date 2020/2/23
 * @Describe: 枚举单例
 */
public enum EnumSingleton {

    INSTANCE;

    public static EnumSingleton getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) throws Exception{
        test1();
    }

    //序列化破坏单例
    public static void test() throws Exception {
        EnumSingleton s;
        EnumSingleton s1 = EnumSingleton.getInstance();

        FileOutputStream fos = new FileOutputStream("EnumSingleton.obj");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(s1);
        oos.close();

        FileInputStream fis = new FileInputStream("EnumSingleton.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        s = (EnumSingleton) ois.readObject();
        ois.close();

        System.out.println(s);
        System.out.println(s1);
        System.out.println(s1 == s);
    }


    //反射破坏单例
    public static void test1() throws Exception{
        //破坏单例
        final Class<EnumSingleton> clazz = EnumSingleton.class;

        //通过反射获取私有构造方法,枚举类构造方法为
        final Constructor<EnumSingleton> c = clazz.getDeclaredConstructor(String.class,int.class);
        //开启强制访问
        c.setAccessible(true);

        //暴力初始化
        final EnumSingleton instance = c.newInstance("123",1);

        //再次调用
        final EnumSingleton instance2 = c.newInstance("123",2);

        System.out.println(instance==instance2);
    }
}
