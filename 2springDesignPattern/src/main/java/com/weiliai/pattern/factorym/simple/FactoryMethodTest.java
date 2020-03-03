package com.weiliai.pattern.factorym.simple;

/**
 * @Author: Doug Li
 * @Date 2020/2/22
 * @Describe: 工厂方法测试类
 */
public class FactoryMethodTest {

    public static void main(String[] args) {
        ICourseFactory factory = new PythonCourseFactory();
        factory.create().record();

        factory=new JavaCourseFactory();
        factory.create().record();
    }
}
