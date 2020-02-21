package com.weiliai.srp.optimize2;

/**
 * @Author: Doug Li
 * @Date 2020/2/21
 * @Describe: 展示职责
 */
public interface ICourseInfo {

    //获取基本信息
    String getCourseName();

    //获取视频流
    byte[] getCourseVideo();
}
