package com.weiliai.pattern.observer.simple.events.mouseevent;

/**
 * @Author: Doug Li
 * @Date 2020/3/15
 * @Describe: 客户端测试
 */
public class MouseEventTest {

    public static void main(String[] args) {
        final MouseEventCallback callback = new MouseEventCallback();
        //注册事件
        final Mouse mouse = new Mouse();
        mouse.addListener(MouseEventType.ON_CLICK,callback);
        mouse.addListener(MouseEventType.ON_DOUBLE_CLICK,callback);
        mouse.addListener(MouseEventType.ON_MOVE,callback);
        mouse.addListener(MouseEventType.ON_DOWN,callback);
        mouse.addListener(MouseEventType.ON_OVER,callback);

        //调用方法
        mouse.click();
    }

}
