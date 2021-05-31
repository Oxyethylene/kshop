package oxy.kshop.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 授权过滤器
 */
@Component
public class AuthFilter extends AbstractSecurityInterceptor implements Filter {
    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Autowired
    private SecurityMetadataSource securityMetadataSource;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        log.info("-----AuthFilter-----");
        FilterInvocation fi = new FilterInvocation(request, response, filterChain);
        // 调用父类的AbstractSecurityInterceptor的方法，也就是调用了accessDecisionManager（MyDecisionManager）
        InterceptorStatusToken token = super.beforeInvocation(fi);

        try {
            // 执行下一个拦截器
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        }finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        // 返回自定义的SecurityMetadataSource
        return this.securityMetadataSource;
    }

    @Override
    @Autowired
    public void setAccessDecisionManager(AccessDecisionManager accessDecisionManager) {
        // 注入自定义的AccessDecisionManager
        super.setAccessDecisionManager(accessDecisionManager);
    }
}
