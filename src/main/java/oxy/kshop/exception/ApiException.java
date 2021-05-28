package oxy.kshop.exception;

import oxy.kshop.enums.ResultCode;

/**
 * @author kudlife
 */
public class ApiException extends RuntimeException {

    private final String msg;
    private final ResultCode resultCode;

    public String getMsg() {
        return msg;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public ApiException() {
        this(ResultCode.FAILED);
    }

    public ApiException(String msg) {
        this(ResultCode.FAILED, msg);
    }

    public ApiException(ResultCode resultCode) {
        this(resultCode, resultCode.getMessage());
    }

    public ApiException(ResultCode resultCode, String msg) {
        super(msg);
        this.resultCode = resultCode;
        this.msg = msg;
    }
}
