package com.weiliai.pattern.delegate.simple;

/**
 * @Author: Doug Li
 * @Date 2020/3/3
 * @Describe: TODO
 */
public class EmployeeB implements IEmployee {

    @Override
    public void doing(String command) {
        System.out.println("我是员工B,我现在开始干"+command+"工作");
    }
}
