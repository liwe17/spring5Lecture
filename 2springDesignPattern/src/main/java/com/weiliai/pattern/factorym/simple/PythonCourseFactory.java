package com.weiliai.pattern.factorym.simple;

/**
 * @Author: Doug Li
 * @Date 2020/2/22
 * @Describe: Python课程工厂
 */
public class PythonCourseFactory implements ICourseFactory {

    @Override
    public ICourse create() {
        return new PythonCourse();
    }
}
