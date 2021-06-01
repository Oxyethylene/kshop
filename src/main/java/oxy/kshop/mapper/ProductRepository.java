package oxy.kshop.mapper;

import org.springframework.stereotype.Repository;
import oxy.kshop.model.entity.ProductDO;
import oxy.kshop.model.vo.ProductVO;

import java.util.List;

@Repository
public interface ProductRepository {
    public List<ProductVO> selectAll();

    public ProductDO findProductById(Long id);

    public int count();
}
