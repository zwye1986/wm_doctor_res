package com.pinde.sci.model.jsres;

import java.io.Serializable;

/**
 * @Author zsq
 * @Date Created in 2021/5/28 10:24
 */
public class ResultVo implements Serializable {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private Object data;

}
