package oxy.kshop.mapper;

import org.springframework.stereotype.Repository;
import oxy.kshop.model.entity.ProductDO;
import oxy.kshop.model.result.PageResult;
import oxy.kshop.model.vo.ProductVO;

import java.util.List;

@Repository
public interface ProductRepository {
    List<ProductVO> selectAll(int page, int size);

    ProductDO selectProductById(Long id);

    String selectNameByProductId(Long id);

    int count();
}
