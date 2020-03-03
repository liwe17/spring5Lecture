package com.weiliai.pattern.proxy.dynamic.case1;

/**
 * @Author: Doug Li
 * @Date 2020/2/26
 * @Describe: 根据订单创建时间自动按照年进行分库,县创建数据源路由对象,使用ThreadLocal的单例实现
 * 动态切换数据源
 */
public class DynamicDataSourceEntry {

    //默认数据源
    public static final String DEFAULT_SOURCE = null;

    private static final ThreadLocal<String> local = new ThreadLocal<>();

    private DynamicDataSourceEntry() {
    }

    //清空数据源
    public static void clear(){
        local.remove();
    }

    //获取当前正在使用的数据源名字
    public static String get(){
        return local.get();
    }

    //还原当前切换的数据库
    public static void restore(){
        local.set(DEFAULT_SOURCE);
    }

    //设置已知名字的数据源
    public static void set(String source){
        local.set(source);
    }

    //根据年份动态设置数据源
    public static void set(int year){
        local.set("DB_"+year);
    }






}
