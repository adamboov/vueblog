package com.adam.vueblog.common.lang;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private int code;

    private String msg;

    private Object data;

    public Result() {
    }

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(String msg, Object data) {

        Result result = new Result(200, msg, data);

        return result;
    }


    public static Result fail(String msg, Object data) {

        Result result = new Result(400, msg, data);

        return result;
    }

    public static Result fail(int code,String msg, Object data) {

        Result result = new Result(code, msg, data);

        return result;
    }
}
