package com.weiliai.pattern.observer.guava;

import com.google.common.eventbus.EventBus;

/**
 * @Author: Doug Li
 * @Date 2020/3/15
 * @Describe: TODO
 */
public class GuavaEventTest {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        final GuavaEvent guavaEvent = new GuavaEvent();
        eventBus.register(guavaEvent);
        eventBus.post("Tom");
    }
}
