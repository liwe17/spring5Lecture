package com.weiliai.pattern.observer.simple;

import java.util.Observable;

/**
 * @Author: Doug Li
 * @Date 2020/3/12
 * @Describe: TODO
 */
public class GPer extends Observable {

    private String name = "GPer 生态圈";

    private static GPer gper;

    private GPer(){}

    public static GPer getInstance(){
        if(null==gper){
            gper = new GPer();
        }
        return gper;
    }

    public String getName() {
        return name;
    }

    public void publishQuestion(GPer gper,Question question){
        System.out.println(question.getUsername()+"在"+gper.getName()+"上提交了一个问题");
        setChanged();
        notifyObservers(question);
    }


    static class Question {

        private String username;

        private String content;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
