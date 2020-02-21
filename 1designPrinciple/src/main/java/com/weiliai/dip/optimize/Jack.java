package com.weiliai.dip.optimize;

/**
 * @Author: Doug Li
 * @Date 2020/2/20
 * @Describe: 依赖注入 1 ,无论jack是否单或多实例,均可以
 */
public class Jack {

    void study(ICourse course){
        course.study();
    }

    public static void main(String[] args) {
        Jack jack = new Jack();
        jack.study(new JavaCourse());
        jack.study(new PythonCourse());
    }
}
