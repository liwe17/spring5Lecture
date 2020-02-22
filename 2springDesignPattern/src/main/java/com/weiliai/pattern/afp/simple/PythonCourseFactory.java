package com.weiliai.pattern.afp.simple;

/**
 * @Author: Doug Li
 * @Date 2020/2/22
 * @Describe: Java产品族的具体工厂
 */
public class PythonCourseFactory implements CourseFactory {

    @Override
    public INote createNote() {
        return new PythonNote();
    }

    @Override
    public IVideo createVideo() {
        return new PythonVideo();
    }
}
