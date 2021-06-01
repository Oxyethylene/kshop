package oxy.kshop.controller.mall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import oxy.kshop.model.entity.UserDO;
import oxy.kshop.mapper.UserRepository;
import oxy.kshop.model.param.LoginParam;
import oxy.kshop.model.param.RegisterParam;
import oxy.kshop.model.vo.UserVO;
import oxy.kshop.service.IUserService;

import java.util.*;

/**
 * @author kudlife
 */
@RestController
@RequestMapping(value = "/user", produces = "application/json;charset=UTF-8")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    final private PasswordEncoder passwordEncoder;

    private final IUserService userService;

    @GetMapping("/all")
    public List<UserDO> findAll() {
        return userRepository.findUserByMap(null);
    }

    @GetMapping("/test")
    public String test(@RequestBody @Validated LoginParam loginParam) {
        if (!"ljunyfor@outlook.com".equals(loginParam.getEmail())) {
            return "email or password error";
        }
        if (!"asdfghj".equals(loginParam.getPassword())) {
            return "email or password error";
        }
        return "login success";
    }

    @PostMapping("/register")
    public UserVO register(@RequestBody @Validated RegisterParam param) {
        return userService.register(param);
    }

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, IUserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }
}
