package com.weiliai.lod.simple;

import java.util.List;

/**
 * @Author: Doug Li
 * @Date 2020/2/21
 * @Describe: 团队领导类
 */
public class TeamLeader {

    public void checkNumberOfCourses(List<Course> courseList){
        System.out.println("目前已经发布的课程数量是:"+courseList.size());
    }
}
