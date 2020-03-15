package com.weiliai.pattern.observer.simple;

/**
 * @Author: Doug Li
 * @Date 2020/3/12
 * @Describe: TODO
 */
public class ObserverTest {

    public static void main(String[] args) {
        final GPer gper = GPer.getInstance();
        final Teacher tom = new Teacher("Tom");
        final Teacher mic = new Teacher("Mic");
        gper.addObserver(tom);
        gper.addObserver(mic);

        //业务逻辑代码
        GPer.Question question = new GPer.Question();
        question.setUsername("小明");
        question.setContent("观察者设计模式适用于那些场景");
        gper.publishQuestion(gper,question);
    }
}
