package com.weiliai.lsp.optimize;

/**
 * @Author: Doug Li
 * @Date 2020/2/21
 * @Describe: 长方形
 */
public class Rectangle implements Quadrangle{

    private long height;

    private long width;

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }
}
