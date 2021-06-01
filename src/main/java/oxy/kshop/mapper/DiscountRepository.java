package oxy.kshop.mapper;

import org.springframework.stereotype.Repository;
import oxy.kshop.model.entity.DiscountDO;

@Repository
public interface DiscountRepository {
    DiscountDO selectDiscountByProductId(Long id);
}
