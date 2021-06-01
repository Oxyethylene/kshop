package oxy.kshop.mapper;

import org.springframework.stereotype.Repository;
import oxy.kshop.model.entity.SkuDO;
import oxy.kshop.model.vo.SkuVO;

import java.util.List;

@Repository
public interface SkuRepository {
    List<SkuVO> selectAllSkuByProductId(Long id);
}
