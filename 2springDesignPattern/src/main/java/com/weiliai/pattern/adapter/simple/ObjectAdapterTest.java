package com.weiliai.pattern.adapter.simple;

/**
 * @Author: Doug Li
 * @Date 2020/3/8
 * @Describe: 适配器测试类
 */
public class ObjectAdapterTest {

    public static void main(String[] args) {
        final DC5 dc5 = new PowerAdapter(new AC220());
        dc5.outputDC5V();
    }

}
