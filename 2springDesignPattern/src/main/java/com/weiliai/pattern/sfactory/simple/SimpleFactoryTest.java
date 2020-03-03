package com.weiliai.pattern.sfactory.simple;

/**
 * @Author: Doug Li
 * @Date 2020/2/22
 * @Describe: 简单工厂测试类
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {
        CourseFactory courseFactory = new CourseFactory();
        courseFactory.create("java").record();
        courseFactory.create(PythonCourse.class).record();
    }



}
