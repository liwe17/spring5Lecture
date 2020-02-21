package com.weiliai.srp.simple;

/**
 * @Author: Doug Li
 * @Date 2020/2/20
 * @Describe: 不使用单一职责
 */
public class Course {

    public void study(String courseName){
        if("直播课".equals(courseName)){
            System.out.println(courseName+"不能快进");
        }else{
            System.out.println(courseName+"可以反复观看");
        }
    }

    public static void main(String[] args) {
        Course course = new Course();
        course.study("直播课");
        course.study("录播课");
    }
}
