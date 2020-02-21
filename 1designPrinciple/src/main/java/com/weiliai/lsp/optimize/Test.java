package com.weiliai.lsp.optimize;

/**
 * @Author: Doug Li
 * @Date 2020/2/21
 * @Describe: TODO
 */
public class Test {

    public static void resize(Rectangle rectangle){
        while(rectangle.getWidth()>=rectangle.getHeight()){
            rectangle.setHeight(rectangle.getHeight()+1);
            System.out.println("width:"+rectangle.getWidth()+",height:"+rectangle.getHeight());
        }
        System.out.println("resize 方法结束 width:"+rectangle.getWidth()+",height:"+rectangle.getHeight());
    }

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(20);
        rectangle.setHeight(10);
        resize(rectangle);

        //里氏替换只存在父类与子类之间,约束继承泛滥.
        Square square = new Square();
        square.setLength(10);
        //resize(square);

    }
}
