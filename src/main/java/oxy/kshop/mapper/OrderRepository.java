package oxy.kshop.mapper;

import org.springframework.stereotype.Repository;
import oxy.kshop.model.entity.OrderDO;
import oxy.kshop.model.vo.OrderVO;

import java.util.List;

@Repository
public interface OrderRepository {
    List<OrderDO> selectCartByUserId(Long id, int page, int size);

    int selectCartSizeByUserId(Long id);
}
