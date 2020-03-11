package com.weiliai.pattern.adapter.simple;

/**
 * @Author: Doug Li
 * @Date 2020/3/8
 * @Describe: 电源适配器
 */
public class PowerAdapter implements DC5 {

    private AC220 ac220;

    public PowerAdapter(AC220 ac220) {
        this.ac220 = ac220;
    }

    @Override
    public int outputDC5V() {
        int adapterInput = ac220.outputAC220V();
        //变压器
        adapterInput =adapterInput/44;
        System.out.println("使用PowerAdapter 输入AC:"+adapterInput+"V 输出DC:"+adapterInput+"V");
        return adapterInput;
    }
}
