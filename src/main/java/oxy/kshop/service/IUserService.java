package oxy.kshop.service;

import oxy.kshop.model.param.LoginParam;
import oxy.kshop.model.vo.UserVO;

public interface IUserService {
    UserVO login(LoginParam loginParam);
}
