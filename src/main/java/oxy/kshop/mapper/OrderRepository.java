package oxy.kshop.mapper;

import org.springframework.stereotype.Repository;
import oxy.kshop.model.entity.OrderDO;
import oxy.kshop.model.vo.OrderVO;

import java.util.List;

@Repository
public interface OrderRepository {
    List<OrderDO> selectCartByUserId(Long id, int page, int size);

    int selectCartSizeByUserId(Long id);

    void save(OrderDO order);

    OrderDO selectCartByOrderId(Long orderId);

    OrderDO selectCartByUserIdAndSkuId(Long userId, Long skuId);

    void updateOrderStatusByOrderId(Long orderId, int status);

    void updateOrderCountByOrderId(Long orderId, int count);

    void deleteCartByOrderId(Long orderId);

    List<OrderDO> selectAllByUserId(Long userId, int page, int size);

    List<OrderDO> selectAllByUserIdAndStatus(Long userId, int status, int page, int size);

    OrderDO selectOrderByOrderId(Long orderId);

    int countOrderByUserId(Long id);

    int countOrderByUserIdAndStatus(Long id, int status);
}
