package oxy.kshop.mapper;

import org.springframework.stereotype.Repository;
import oxy.kshop.model.entity.ResourceDO;

import java.util.Set;

@Repository
public interface ResourceRepository {
    Set<Long> selectIdsByUserId(Long userId);

    Set<ResourceDO> selectAllResource();
}
