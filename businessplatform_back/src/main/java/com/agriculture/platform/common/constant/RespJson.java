package com.agriculture.platform.common.constant;

import java.io.Serializable;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/2
 */
public class RespJson<T> implements Serializable {
    //返回消息代码
    private int code;
    //返回信息
    private String message;
    //数据
    private T data;

    public RespJson(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public RespJson(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public RespJson(T data) {
        this.code = Result.SUCCESS.getCode();
        this.message = Result.SUCCESS.getMessage();
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RespJson{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
