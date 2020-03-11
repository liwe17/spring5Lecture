package com.weiliai.pattern.adapter.simple;

/**
 * @Author: Doug Li
 * @Date 2020/3/8
 * @Describe: 表示AC220V
 */
public class AC220 {

    public int outputAC220V(){
        int output = 220;
        System.out.println("输出交流电"+output+"V");
        return output;
    }
}
