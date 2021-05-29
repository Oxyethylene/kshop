package oxy.kshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import oxy.kshop.config.security.UserDetail;
import oxy.kshop.exception.ApiException;
import oxy.kshop.mapper.UserRepository;
import oxy.kshop.model.entity.UserDO;
import oxy.kshop.model.param.LoginParam;
import oxy.kshop.model.vo.UserVO;
import oxy.kshop.service.IUserService;
import oxy.kshop.util.JwtUtil;

import java.util.Collections;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserVO login(LoginParam loginParam) {
        UserDO userDO = userRepository.findUserByEmail(loginParam.getEmail());
        if (userDO == null || !passwordEncoder.matches(loginParam.getPassword(), userDO.getPassword())) {
            throw new ApiException("用户名或密码错误");
        }

        UserVO userVO = new UserVO();
        userVO.setId(userDO.getId());
        userVO.setName(userDO.getName());
        userVO.setToken(jwtUtil.generate(userDO.getEmail()));

        return userVO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDO user = userRepository.findUserByEmail(email);

        if (user == null) {
            // 该异常会在MyEntryPoint中接收并进行处理
            throw new UsernameNotFoundException("没有找到该用户");
        }

        return new UserDetail(user, Collections.emptyList());
    }
}
