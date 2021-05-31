package oxy.kshop.config.security;

import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import oxy.kshop.service.impl.UserServiceImpl;
import oxy.kshop.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证过滤器
 * 从请求头获取jwt，解析后组装一个Authentication对象放入SpringSecurity上下文中，即可完成登陆验证
 *
 * @author kudlife
 */
@Component
public class LoginFilter extends OncePerRequestFilter {
    private final Logger log = LoggerFactory.getLogger(LoginFilter.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("------LoginFilter------");
        Claims claims = jwtUtil.parse(request.getHeader("Authorization"));
        if (claims != null) {
            // 从jwt中取出email
            String email = claims.getSubject();
            // 查询出用户对象
            UserDetails user = userService.loadUserByUsername(email);
            // 组装一个认证对象并放置到上下文中
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
