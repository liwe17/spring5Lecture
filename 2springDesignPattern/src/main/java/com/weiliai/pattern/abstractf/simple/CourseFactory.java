package com.weiliai.pattern.abstractf.simple;

/**
 * @Author: Doug Li
 * @Date 2020/2/22
 * @Describe:
 *  抽象工厂是用户的主入口
 *  是Spring中应用得最广泛的一种设计模式
 *  易于扩展
 */
public interface CourseFactory {

    INote createNote();

    IVideo createVideo();
}
