package oxy.kshop.controller.mall;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oxy.kshop.config.security.UserDetail;

import java.util.Collection;

@RestController
@RequestMapping("/role")
public class RoleController {
    @PostMapping(produces = "application/json;charset=utf-8")
    public String addRole() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        final UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        return "user " + name + " is exec addRole(), toString = " + authentication.toString();
    }
}
