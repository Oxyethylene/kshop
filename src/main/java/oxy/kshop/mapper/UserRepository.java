package oxy.kshop.mapper;

import org.springframework.stereotype.Repository;
import oxy.kshop.model.entity.UserDO;

import java.util.List;
import java.util.Map;

/**
 * @author kudlife
 */
@Repository
public interface UserRepository {
    boolean save(UserDO userDO);

    boolean delete(UserDO userDO);

    boolean deleteUserByMap(Map<String, Object> map);

    boolean updateUser(UserDO userDO);

    List<UserDO> findUserByMap(Map<String, Object> map);

    UserDO findUserByEmail(String email);
}
