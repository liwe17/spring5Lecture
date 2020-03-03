package com.weiliai.pattern.template.simple;

/**
 * @Author: Doug Li
 * @Date 2020/3/3
 * @Describe: TODO
 */
public abstract class NetworkCourse {

    protected final void createCourse(){

        //发布预习资料
        this.postPreResource();

        //制作课程PPT
        this.createPPT();

        //在线直播
        this.liveVideo();

        //提交课程笔记
        this.postNote();

        //提交源码
        this.postSource();

        //布置作业,有些课是没有作业的,有些是有作业的
        //有作业检查,没有流程结束
        if(needHomeWork()){
            checkHomeWork();
        }
    }

    abstract void checkHomeWork();

    //钩子方法,实现流程的微调
    protected boolean needHomeWork(){
        return false;
    }


    final void postSource(){
        System.out.println("提交源代码");
    }
    
    final void postNote(){
        System.out.println("提交课件和笔记");
    }
    
    final void liveVideo(){
        System.out.println("直播授课");
    }
    
    final void createPPT(){
        System.out.println("创建备课PPT");
    }

    final void postPreResource(){
        System.out.println("分发预习资料");
    }


}
