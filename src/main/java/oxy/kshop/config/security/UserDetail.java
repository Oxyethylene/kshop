package oxy.kshop.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import oxy.kshop.model.entity.UserDO;

import java.util.Collection;

/**
 * UserDetails是SpringSecurity用户对象的接口，
 * User类对该接口做了默认实现
 */
public class UserDetail extends User {
    private UserDO userDO;

    public UserDO getUserDO() {
        return userDO;
    }

    public void setUserDO(UserDO userDO) {
        this.userDO = userDO;
    }

    public UserDetail(UserDO userDO, Collection<? extends GrantedAuthority> authorities) {
        super(userDO.getName(), userDO.getPassword(), authorities);
        this.userDO = userDO;
    }
}
