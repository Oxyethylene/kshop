package oxy.kshop.service;

import oxy.kshop.model.entity.UserDO;

public interface IUserService {
    UserDO login(UserDO userDO);
}
