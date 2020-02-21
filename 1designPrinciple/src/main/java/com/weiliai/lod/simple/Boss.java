package com.weiliai.lod.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Doug Li
 * @Date 2020/2/21
 * @Describe: 老板类
 */
public class Boss {

    public void commandCheckNumber(TeamLeader teamLeader){
        //模拟boss 一页一页往下翻页,TeamLeader实时统计
        List<Course> courseList = new ArrayList<Course>();
        for (int i = 0; i < 20; i++) {
            courseList.add(new Course());
        }
        teamLeader.checkNumberOfCourses(courseList);
    }
}
