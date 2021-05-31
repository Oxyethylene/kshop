package oxy.kshop.controller.mall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import oxy.kshop.model.param.LoginParam;
import oxy.kshop.model.vo.UserVO;
import oxy.kshop.service.IUserService;
import oxy.kshop.service.impl.UserServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    IUserService userService;

    @PostMapping("/login")
    public UserVO login(@RequestBody @Valid LoginParam loginParam) {
        return userService.login(loginParam);
    }

    @GetMapping("/loginTest")
    public String test() {
        log.info("-----test-----");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.toString());
        return "认证通过";
    }
}
