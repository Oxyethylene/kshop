package oxy.kshop.model.vo;

import oxy.kshop.enums.ResultCode;

/**
 * @author kudlife
 */
public class ResultVO<T> {
    private Integer code;

    private String message;

    private T data;

    public ResultVO(){}

    public ResultVO(T data) {
        this(ResultCode.SUCCESS, data);
    }

    public ResultVO(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

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

    @Override
    public String toString() {
        return String.format("{\"code\": %d, \"message\": \"%s\", \"data\": \"%s\"", code, message, data.toString());
    }
}
