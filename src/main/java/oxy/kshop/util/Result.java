package oxy.kshop.util;

public class Result<T> {
    private String status;

    private String message;

    private T data;

    public void success(String message, T data) {
        this.status = ResultConst.SUCCESS;
        this.message = message;
        this.data = data;
    }

    public void error(String message, T data) {
        this.status = ResultConst.ERROR;
        this.message = message;
        this.data = data;
    }

    public void permissionDenied(String message, T data) {
        this.status = ResultConst.PERMISSION_DENIED;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
