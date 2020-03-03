package com.weiliai.pattern.singleton.simple;

import java.io.*;

/**
 * @Author: Doug Li
 * @Date 2020/2/23
 * @Describe: TODO
 */
public class SerializableSingleton implements Serializable {

    //序列化就是把内存中的状态通过转换成字节码的形式
    //从而转换一个I/O流,写入其他地方(磁盘/网络IO)
    //内存中的状态会永久保留下来


    //反序列化就是将已经持久化的字节码内容转为I/O流
    //通过I/O流的读取,转为JAVA对象
    //在转换过程中会重新new对象


    private SerializableSingleton() {

    }

    public static final SerializableSingleton instance = new SerializableSingleton();

    public static SerializableSingleton getInstance() {
        return instance;
    }

    //防止序列化破坏单例,但仍创建了对象,只不过返回的是我们自己的对象,不是新建的
    private Object readResolve() {
        return instance;
    }

    public static void main(String[] args) throws Exception{
        //序列化破坏单例
        SerializableSingleton s;
        SerializableSingleton s1 = SerializableSingleton.getInstance();

        FileOutputStream fos = new FileOutputStream("SerializableSingleton.obj");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(s1);
        oos.close();

        FileInputStream fis = new FileInputStream("SerializableSingleton.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        s = (SerializableSingleton) ois.readObject();
        ois.close();

        System.out.println(s);
        System.out.println(s1);
        System.out.println(s1 == s);
    }

}
