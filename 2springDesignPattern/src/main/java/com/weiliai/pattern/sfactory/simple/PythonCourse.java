package com.weiliai.pattern.sfactory.simple;

/**
 * @Author: Doug Li
 * @Date 2020/2/22
 * @Describe: Python课程
 */
public class PythonCourse implements ICourse {

    public void record() {
        System.out.println("录制 Python 课程");
    }

    public static void main(String[] args) {
        ICourse course = new PythonCourse();
        course.record();
    }
}
