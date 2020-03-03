package com.weiliai.pattern.proxy.stic.simple;

/**
 * @Author: Doug Li
 * @Date 2020/2/26
 * @Describe: TODO
 */
public class Father {

    private Son son;

    //没办法扩展
    public Father(Son son){
        this.son = son;
    }

    //获取目标对象的引用
    public void findLove(){
        System.out.println("父亲物色对象");
        this.son.findLove();
        System.out.println("双方同意交往,确认关系");
    }

    public static void main(String[] args) {
        //只能帮儿子找对象,不能帮陌生人
        new  Father(new Son()).findLove();;
    }
}
