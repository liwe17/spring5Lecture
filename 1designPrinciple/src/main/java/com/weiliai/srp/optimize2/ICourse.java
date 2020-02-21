package com.weiliai.srp.optimize2;

/**
 * @Author: Doug Li
 * @Date 2020/2/21
 * @Describe: 随着业务扩展,课程要做权限,没有付费可以获取课程基本信息,已经付费可以获取视频流
 *  此接口承担了展示职责+管理职责,不复合单一职责的原则
 */
public interface ICourse {

    //获取基本信息
    String getCourseName();

    //获取视频流
    byte[] getCourseVideo();

    //学习课程
    void studyCourse();

    //退款
    void refundCourse();
}
