package com.weiliai.pattern.abstractf.simple;

/**
 * @Author: Doug Li
 * @Date 2020/2/22
 * @Describe: 抽象工厂方法测试
 *  如果扩展产品等级源码,违背了开闭原则,但是在实际应用中,代码的升级是无法避免的
 */
public class AbstractFactoryTest {

    public static void main(String[] args) {
        CourseFactory courseFactory = new JavaCourseFactory();
        courseFactory.createNote().edit();
        courseFactory.createVideo().record();
    }

}
