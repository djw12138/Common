package com.djw.enums;

/**
 * 标准状态码
 */
public enum ResultTypeEnum {
    SUCCESS(0,"操作成功"),
    FAIL(1001,"系统异常");

    public int code;
    public String message;


    ResultTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}