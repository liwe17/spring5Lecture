package com.weiliai.pattern.prototype.simple.deepclone;

import java.io.Serializable;

/**
 * @Author: Doug Li
 * @Date 2020/2/24
 * @Describe: 金箍棒类
 */
public class JinGuBang implements Serializable {

    public float h =100;

    public float d =10;

    public void big(){
        this.d *= 2;
        this.h *= 2;
    }

    public void small(){
        this.d /= 2;
        this.h /= 2;
    }
}
