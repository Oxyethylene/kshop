package oxy.kshop.mapper;

import org.springframework.stereotype.Repository;
import oxy.kshop.model.entity.SkuDO;
import oxy.kshop.model.vo.SkuVO;

import java.util.List;
import java.util.Map;

@Repository
public interface SkuRepository {
    List<SkuVO> selectAllSkuByProductId(Long id);

    SkuVO selectSkuBySkuId(Long id);

    String selectProductNameBySkuId(Long id);

    void updateSku(Map<String, Object> map);
}
