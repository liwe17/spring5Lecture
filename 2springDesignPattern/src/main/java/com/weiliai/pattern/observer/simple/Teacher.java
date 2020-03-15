package com.weiliai.pattern.observer.simple;

import java.util.Observable;
import java.util.Observer;

/**
 * @Author: Doug Li
 * @Date 2020/3/12
 * @Describe: TODO
 */
public class Teacher implements Observer {

    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        GPer gper = (GPer) o;
        GPer.Question question = (GPer.Question) arg;
        System.err.println("GPer:"+gper+" Question:"+question);
        System.out.println("========================");
        System.out.println(this.name+"老师,你好! 您收到了一个来自"+gper.getName()+"的提问,希望您解答,问题内容如下:"+question.getContent()+"提问者:"+question.getUsername());

    }
}
