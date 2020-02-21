package com.weiliai.dip.optimize;

/**
 * @Author: Doug Li
 * @Date 2020/2/20
 * @Describe: 依赖注入 3 set方法注入,如果jack3是单例的时候,适合使用,多例也适用`
 */
public class Jack3 {

    private ICourse course;

    public void setCourse(ICourse course) {
        this.course = course;
    }

    public Jack3(){
    }

    void study(){
        this.course.study();
    }

    public static void main(String[] args) {
        Jack3 jack3 = new Jack3();
        jack3.setCourse(new JavaCourse());
        jack3.study();
    }
}
