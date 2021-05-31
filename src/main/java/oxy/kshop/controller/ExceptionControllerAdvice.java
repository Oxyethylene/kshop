package oxy.kshop.controller;

import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import oxy.kshop.enums.ResultCode;
import oxy.kshop.exception.ApiException;
import oxy.kshop.model.vo.ResultVO;

/**
 * 异常全局控制
 *
 * @author kudlife
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(ApiException.class)
    public ResultVO<String> apiExceptionHandler(ApiException e) {
        return new ResultVO<>(e.getResultCode(), e.getMsg());
    }

    // 处理参数校验不合格异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        final ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, objectError.getDefaultMessage());
    }

    /// 处理Http请求类型不支持的异常
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultVO<String[]> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return new ResultVO<>(ResultCode.UNSUPPORTED_METHOD, e.getSupportedMethods());
    }
}
