package com.weiliai.pattern.sfp.simple;

/**
 * @Author: Doug Li
 * @Date 2020/2/22
 * @Describe: 课程工厂
 */
public class CourseFactory {

    public ICourse create(String name){
        if("java".equals(name)){
            return new JavaCourse();
        }else if("python".equals(name)){
            return new PythonCourse();
        }else{
            return null;
        }
    }

    public ICourse create(Class<? extends ICourse> clazz) {
        try {
            if (null != clazz) {
                return clazz.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
