package com.weiliai.pattern.factorym.simple;

/**
 * @Author: Doug Li
 * @Date 2020/2/22
 * @Describe: java课程工厂
 */
public class JavaCourseFactory implements ICourseFactory {

    @Override
    public ICourse create() {
        return new JavaCourse();
    }
}
