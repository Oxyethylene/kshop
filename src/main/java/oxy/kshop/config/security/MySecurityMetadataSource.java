package oxy.kshop.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import oxy.kshop.mapper.ResourceRepository;
import oxy.kshop.model.entity.ResourceDO;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 获取当前请求的鉴权规则
 */
@Component
public class MySecurityMetadataSource implements SecurityMetadataSource {
    @Autowired
    ResourceRepository resourceRepository;

    // 作为系统所有接口资源的缓存
    private static final Set<ResourceDO> RESOURCE = new HashSet<>();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if (RESOURCE.isEmpty()) {
            RESOURCE.addAll(resourceRepository.selectAllResource());
        }
        FilterInvocation filterInvocation = (FilterInvocation) o;
        HttpServletRequest request = filterInvocation.getRequest();
        // 遍历所有权限资源，以和当前请求进行匹配
        for (ResourceDO resource : RESOURCE) {
            // 冒号前是方法，冒号后是路径
            String[] split = resource.getPath().split(":");
            // 使用AntPathRequestMatcher可以匹配带路径参数的请求
            AntPathRequestMatcher ant = new AntPathRequestMatcher(split[1]);
            if (request.getMethod().equals(split[0]) && ant.matches(request)) {
                // SecurityConfig是ConfigAttribute的简单实现, 返回了资源的id
                return Collections.singleton(new SecurityConfig(resource.getId().toString()));
            }
        }

        // 请求无需权限
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
