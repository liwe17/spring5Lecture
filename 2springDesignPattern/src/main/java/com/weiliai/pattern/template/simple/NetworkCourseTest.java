package com.weiliai.pattern.template.simple;

/**
 * @Author: Doug Li
 * @Date 2020/3/3
 * @Describe: TODO
 */
public class NetworkCourseTest {

    public static void main(String[] args) {
        System.out.println("---Java 架构师课程---");
        new JavaCourse().createCourse();

        System.out.println("---大数据课程---");
        new BigDataCourse(true).createCourse();
    }
}
