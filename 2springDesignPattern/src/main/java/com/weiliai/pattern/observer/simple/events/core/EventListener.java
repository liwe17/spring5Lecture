package com.weiliai.pattern.observer.simple.events.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Doug Li
 * @Date 2020/3/15
 * @Describe: 监听器,它就是观察者的桥梁
 */
public class EventListener {

    //JDK底层Listener通常也是这样来设计
    protected Map<String, Event> events = new HashMap<>();

    //通过事件名称和一个目标对象来触发事件
    public void addListener(String eventType, Object target) {
        try {
            this.addListener(eventType, target, target.getClass().getMethod("on"+toUpperFirstCase(eventType),Event.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addListener(String eventType, Object target, Method callback) {
        //注册事件
        events.put(eventType, new Event(target, callback));
    }

    //触发,只要有动作就触发
    private void trigger(Event event) {
        event.setSource(this);
        event.setTime(System.currentTimeMillis());
        //发起回调
        if (event.getCallback() != null) {
            try {
                event.getCallback().invoke(event.getTarget(), event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //事件名称触发
    protected void trigger(String trigger) {
        if (!this.events.containsKey(trigger)) {
            return;
        }
        trigger(this.events.get(trigger).setTrigger(trigger));
    }

    //首字母大写
    private String toUpperFirstCase(String str){
        final char[] chars = str.toCharArray();
        chars [0] -=32;
        return String.valueOf(chars);
    }


}