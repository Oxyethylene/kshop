package oxy.kshop.enums;

/**
 * @author kudlife
 */

public enum ResultCode {
    //
    SUCCESS(1000, "操作成功"),

    UNAUTHORIZED(1001, "没有登陆"),

    FORBIDDEN(1002, "没有权限"),

    VALIDATE_FAILED(1003, "参数校验失败"),

    FAILED(1004, "接口异常"),

    UNSUPPORTED_METHOD(5001, "方法不支持"),

    ERROR(5000, "未知错误");

    private int code;

    private String message;

    ResultCode(int code, String message) {
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
