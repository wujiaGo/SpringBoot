package com.org.pool;

public class Result<T> {

    private Integer code;
    private String message;
    private T data;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Result ok(Object o) {
        return new Result(0, "成功", o);
    }

    public static Result<Object> error(String s) {
        return new Result(1000, s);
    }

    public Result() {
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.setMessage(msg);
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.data = data;
        this.setMessage(msg);
    }

}
