package com.shang.spray.base;

/**
 * info:状态码
 * Created by shang on 16/7/1.
 */
public enum  BaseApiCode {


    STATUS_200(200, "OK"),
    STATUS_400(400, "ERROR"),
    STATUS_500(500, "Exception"),

    STATUS_1100(1100, "参数不能为空");


    private int status;

    private String msg;

    BaseApiCode(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
