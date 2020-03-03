package com.weiliai.pattern.prototype.simple;

import com.weiliai.pattern.prototype.simple.clone.Client;
import com.weiliai.pattern.prototype.simple.clone.ConcretePrototypeA;
import com.weiliai.pattern.prototype.simple.deepclone.QiTanDaShena;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Doug Li
 * @Date 2020/2/24
 * @Describe: 原型模式测试
 */
public class PrototypeTest {

    public static void main(String[] args) {
//        test();
        test1();
    }


    //浅拷贝测试
    private static void test() {
        //创建一个待克隆的对象
        final ConcretePrototypeA concretePrototypeA = new ConcretePrototypeA();
        List<String> hobbies = new ArrayList<String>();
        concretePrototypeA.setName("prototype");
        concretePrototypeA.setAge(18);
        concretePrototypeA.setHobbies(hobbies);
        System.out.println(concretePrototypeA);

        //创建Client对象,准备开始克隆
        final ConcretePrototypeA prototype = (ConcretePrototypeA) new Client(concretePrototypeA).startClone(concretePrototypeA);
        System.out.println(prototype);

        System.out.println("克隆对象中引用类型地址值:"+prototype.getHobbies());
        System.out.println("原型对象中引用类型地址值:"+concretePrototypeA.getHobbies());
        System.err.println(prototype.getHobbies()==concretePrototypeA.getHobbies()); //意味着hobbies修改任意一个值,原型和克隆的实体都会发生变化
    }

    private static void test1(){
        QiTanDaShena qiTanDaShena = new QiTanDaShena();
        try {
            QiTanDaShena clone = (QiTanDaShena) qiTanDaShena.clone();
            System.out.println("深克隆:"+(clone.jinGuBang==qiTanDaShena.jinGuBang));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        QiTanDaShena q = new QiTanDaShena();
        QiTanDaShena n = q.shallowClone(q);
        System.out.println("浅克隆:"+(q.jinGuBang==n.jinGuBang));
    }
}
