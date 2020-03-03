package com.weiliai.pattern.abstractf.simple;

/**
 * @Author: Doug Li
 * @Date 2020/2/22
 * @Describe: 扩展产品等级Python课堂笔记类
 */
public class PythonNote implements INote {

    @Override
    public void edit() {
        System.out.println("编写 Python 笔记");
    }
}
