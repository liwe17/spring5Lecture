package com.weiliai.pattern.decorator.simple.v2;

/**
 * @Author: Doug Li
 * @Date 2020/3/12
 * @Describe: TODO
 */
public class BatterCakeTest {

    public static void main(String[] args) {

        BatterCake batterCake;
        //路边摊买一个煎饼
        batterCake = new BaseBatterCake();
        //煎饼有点小,加一个鸡蛋
        batterCake = new EggDecorator(batterCake);
        //在加一个鸡蛋
        batterCake = new EggDecorator(batterCake);
        //很饿,加个香肠
        batterCake = new SausageDecorator(batterCake);

        //跟静态代理最大的区别就是职责不同
        //静态代理不一定满足is-a关系
        //静态代理会做功能增强,是一个职责变得不一样

        //装饰者模式更多考虑的是扩展
        System.out.println(batterCake.getMsg()+",总价格:"+batterCake.getPrice());

    }

}
