package com.weiliai.pattern.adapter.logincase;

/**
 * @Author: Doug Li
 * @Date 2020/3/8
 * @Describe: TODO
 */
public class ResultMsg {

    private int code;

    private String msg;

    private Object data;

    public ResultMsg(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
