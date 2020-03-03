package com.weiliai.pattern.abstractf.simple;

/**
 * @Author: Doug Li
 * @Date 2020/2/22
 * @Describe: JAVA产品族的Java视频类
 */
public class JavaVideo implements IVideo {

    @Override
    public void record() {
        System.out.println("录制 JAVA 视频");
    }
}
