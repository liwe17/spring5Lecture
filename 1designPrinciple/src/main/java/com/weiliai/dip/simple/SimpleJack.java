package com.weiliai.dip.simple;


/**
 * @Author: Doug Li
 * @Date 2020/2/20
 * @Describe: 不使用依赖倒置原则
 */
public class SimpleJack {

    public void studyJavaCourse(){
        System.out.println("Jack 在学习 Java 的课程");
    }

    public void studyPythonCourse(){
        System.out.println("Jack 在学习 Python 的课程");
    }

    public static void main(String[] args) {
        SimpleJack simpleJack = new SimpleJack();
        simpleJack.studyJavaCourse();
        simpleJack.studyPythonCourse();
    }

}
