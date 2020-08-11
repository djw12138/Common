package com.djw.domain;


/**
 * 返回数据封装
 */
public class RestfulDataResponse<T> extends RestfulResponse {

    /**
     * 空构造函数
     */
    public RestfulDataResponse() {
    }

    /**
     * 有参构造函数
     */
    public RestfulDataResponse(int code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    /**
     * 实体类型
     */
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "RestfulDataResponse{" +
                "data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
