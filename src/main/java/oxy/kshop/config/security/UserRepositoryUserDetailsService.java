package oxy.kshop.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import oxy.kshop.model.entity.UserDO;
import oxy.kshop.mapper.UserRepository;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDO userDO = userRepo.findUserByEmail(s);
        if (userDO == null) {
            throw new UsernameNotFoundException("User '" + s + "' email not found");
        }
        return userDO;
    }

    @Autowired
    public UserRepositoryUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
}
