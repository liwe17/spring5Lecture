package com.weiliai.pattern.observer.simple.events.mouseevent;

import com.weiliai.pattern.observer.simple.events.core.Event;

/**
 * @Author: Doug Li
 * @Date 2020/3/15
 * @Describe: TODO
 */
public class MouseEventCallback {

    public void onClick(Event e){
        System.out.println("=========触发鼠标单击事件============"+"\n"+e);
    }


    public void onDoubleClick(Event e){
        System.out.println("=========触发鼠标双击事件============"+"\n"+e);
    }

    public void onUp(Event e){
        System.out.println("=========触发鼠标弹起事件============"+"\n"+e);
    }

    public void onDown(Event e){
        System.out.println("=========触发鼠标按下事件============"+"\n"+e);
    }

    public void onMove(Event e){
        System.out.println("=========触发鼠标移动事件============"+"\n"+e);
    }

    public void onWheel(Event e){
        System.out.println("=========触发鼠标滚动事件============"+"\n"+e);
    }

    public void onOver(Event e){
        System.out.println("=========触发鼠标悬停事件============"+"\n"+e);
    }

    public void onBlur(Event e){
        System.out.println("=========触发鼠标失焦事件============"+"\n"+e);
    }

    public void onFocus(Event e){
        System.out.println("=========触发鼠标获焦事件============"+"\n"+e);
    }

}
