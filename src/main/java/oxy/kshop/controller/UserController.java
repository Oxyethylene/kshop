package oxy.kshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import oxy.kshop.entity.User;
import oxy.kshop.mapper.UserRepository;
import oxy.kshop.util.Result;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public List<User> findAll() {
        return userRepository.find(null);

    }

    @PostMapping("/save")
    public Result<User> save(@RequestBody User user) {
        Result<User> result = new Result<>();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("email", user.getEmail());
        if (userRepository.find(queryMap).size() > 0) {
            result.error("email aleady exist", null);
            return result;
        }
        // ignore user info check
        log.debug("user = {}", user);
        Date currentTime = new Date();
        user.setCreateDate(currentTime);
        user.setModifiedDate(currentTime);
        log.debug("user = {}", user);
        userRepository.save(user);
        log.info("saved new user {}", user);
        user = userRepository.find(queryMap).get(0);
        user.setPassword(null);
        result.success("sign up success", user);
        return result;
    }
}
