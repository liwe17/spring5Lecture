package com.weiliai.pattern.abstractf.simple;

/**
 * @Author: Doug Li
 * @Date 2020/2/22
 * @Describe: Python产品族的Python视频类
 */
public class PythonVideo implements IVideo {

    @Override
    public void record() {
        System.out.println("录制 Python 视频");
    }
}
