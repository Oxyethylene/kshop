package oxy.kshop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import oxy.kshop.config.security.UserDetail;
import oxy.kshop.exception.ApiException;
import oxy.kshop.mapper.UserRepository;
import oxy.kshop.model.entity.UserDO;
import oxy.kshop.model.param.LoginParam;
import oxy.kshop.model.param.RegisterParam;
import oxy.kshop.model.vo.UserVO;
import oxy.kshop.service.IResourceService;
import oxy.kshop.service.IUserService;
import oxy.kshop.util.JwtUtil;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService, UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    IResourceService resourceService;

    @Override
    public UserVO login(LoginParam loginParam) {
        UserDO userDO = userRepository.findUserByEmail(loginParam.getEmail());
        // 用户不存在或者密码不正确
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

        // 查出用户全部的资源id，再转换为SimpleGrantedAuthority对象
        log.debug(user.getId().toString());
        Set<SimpleGrantedAuthority> authorities = resourceService.getIdsByUserId(user.getId())
                .stream()
                .map(String::valueOf)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        log.debug(authorities.toString());

        return new UserDetail(user, authorities);
    }

    @Override
    public UserVO register(RegisterParam param) {
        UserDO user = userRepository.findUserByEmail(param.getEmail());
        if (user != null) {
            throw new ApiException("邮箱已注册");
        }
        user = new UserDO();
        user.setName(param.getName());
        user.setEmail(param.getEmail());
        // 密码记得加密后再存入数据库
        user.setPassword(passwordEncoder.encode(param.getPassword()));
        user.setCreateDate(new Date());
        user.setModifiedDate(new Date());
        userRepository.save(user);
        return login(new LoginParam(param.getEmail(), param.getPassword()));
    }
}
