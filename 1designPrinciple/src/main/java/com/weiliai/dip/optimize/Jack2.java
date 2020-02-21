package com.weiliai.dip.optimize;

/**
 * @Author: Doug Li
 * @Date 2020/2/20
 * @Describe: 依赖注入 2,构造器注入 如果Jack2是多实例,适用
 */
public class Jack2 {

    private ICourse course;

    public Jack2(ICourse course) {
        this.course = course;
    }

    void study(){
        this.course.study();
    }

    public static void main(String[] args) {
        Jack2 jack2 = new Jack2(new JavaCourse());
        jack2.study();
    }
}
