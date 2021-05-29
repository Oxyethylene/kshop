package oxy.kshop.controller.mall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import oxy.kshop.model.entity.UserDO;
import oxy.kshop.mapper.UserRepository;
import oxy.kshop.model.param.LoginParam;
import oxy.kshop.model.vo.UserVO;

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
    public UserVO register(@RequestBody UserDO userDO) {
        Map<String, Object> queryMap = new HashMap<>();
        // ignore user info check
        log.debug("user = {}", userDO);
        Date currentTime = new Date();
        userDO.setCreateDate(currentTime);
        userDO.setModifiedDate(currentTime);
        userDO.setPassword(passwordEncoder.encode(userDO.getPassword()));
        log.debug("user = {}", userDO);
        userRepository.save(userDO);
        log.info("saved new user {}", userDO);
        userDO = userRepository.findUserByEmail(userDO.getEmail());
        userDO.setPassword(null);
        final UserVO userVO = new UserVO();
        userVO.setId(userDO.getId());
        userVO.setName(userDO.getName());
        userVO.setToken("ahdiuwoq.adwhqod.dwqd");
        userVO.setResources(new HashSet<String>(){{
            addAll(Arrays.asList("ROLE_USER", "ROLE_ADMIN", "ROLE_SELLER"));}});
        return userVO;
    }

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
}
