package oxy.kshop.config.security;

import io.jsonwebtoken.lang.Collections;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;

@Component
public class MyDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        // 如果授权规则为空代表URL无需权限
        if (Collections.isEmpty(configAttributes)) {
            return;
        }

        // 判断授权规则和当前用户是否匹配
        for (ConfigAttribute configAttribute : configAttributes) {
            // 匹配上代表用户拥有对应权限
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (Objects.equals(authority.getAuthority(), configAttribute.getAttribute())) {
                    return;
                }
            }
        }

        // 抛出异常，由AccessDeniedHandler处理授权异常
        throw new AccessDeniedException("没有相关权限");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
