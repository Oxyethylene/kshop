package oxy.kshop.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import oxy.kshop.enums.ResultCode;
import oxy.kshop.model.vo.ResultVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 认证异常处理器
 * 查询用户失败或校验密码失败时抛出的异常在此处理
 */
public class MyEntryPoint implements AuthenticationEntryPoint {
    Logger log = LoggerFactory.getLogger(MyEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.error(e.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        ResultVO<String> resultVO = new ResultVO<>(ResultCode.UNAUTHORIZED, "没有登陆");
        writer.write(resultVO.toString());
        writer.flush();
        writer.close();
    }
}
