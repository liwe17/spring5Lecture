package com.weiliai.lod.optimize;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Doug Li
 * @Date 2020/2/21
 * @Describe: 团队领导类
 */
public class TeamLeader {

    public void checkNumberOfCourses(){
        List<Course> courseList = new ArrayList<Course>();
        for (int i = 0; i < 20; i++) {
            courseList.add(new Course());
        }
        System.out.println("目前已经发布的课程数量是:"+courseList.size());
    }
}
