package oxy.kshop.mapper;

import org.springframework.stereotype.Repository;
import oxy.kshop.entity.User;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository {
    void save(User user);

    List<User> find(Map<String, Object> map);
}
