package com.weiliai.pattern.delegate.simple;

/**
 * @Author: Doug Li
 * @Date 2020/3/3
 * @Describe: TODO
 */
public class Boss {

    public void command(String command,Leader leader){
        leader.doing(command);
    }
}
