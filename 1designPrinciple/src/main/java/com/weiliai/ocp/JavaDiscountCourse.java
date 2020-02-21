package com.weiliai.ocp;

/**
 * @Author: Doug Li
 * @Date 2020/2/20
 * @Describe: JAVA课程优惠
 */
public class JavaDiscountCourse extends JavaCourse{

    public JavaDiscountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }

    public Double getOriginPrice(){
        return super.getPrice();
    }

    public Double getDisCountPrice(){
        return super.getPrice() * 0.61;
    }
}
