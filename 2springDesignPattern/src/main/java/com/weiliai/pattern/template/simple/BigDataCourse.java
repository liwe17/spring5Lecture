package com.weiliai.pattern.template.simple;

/**
 * @Author: Doug Li
 * @Date 2020/3/3
 * @Describe: TODO
 */
public class BigDataCourse extends NetworkCourse {

    private boolean needHomeWorkFlag;

    public BigDataCourse(boolean needHomeWorkFlag) {
        this.needHomeWorkFlag = needHomeWorkFlag;
    }

    @Override
    void checkHomeWork() {
        System.out.println("检查大数据的课后作业");
    }

    @Override
    protected boolean needHomeWork() {
        return this.needHomeWorkFlag;
    }
}
