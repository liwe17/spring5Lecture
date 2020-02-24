package com.weiliai.pattern.pp.simple.clone;

/**
 * @Author: Doug Li
 * @Date 2020/2/24
 * @Describe: 客户端
 */
public class Client {

    private Prototype prototype;

    public Client(Prototype prototype) {
        this.prototype = prototype;
    }

    public Prototype startClone(Prototype prototype){
        return prototype.clone();
    }
}
