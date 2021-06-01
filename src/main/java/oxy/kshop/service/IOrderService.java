package oxy.kshop.service;

import oxy.kshop.model.param.PageParam;
import oxy.kshop.model.result.PageResult;
import oxy.kshop.model.vo.OrderVO;

import java.util.List;

public interface IOrderService {
    PageResult<OrderVO> getOrdersByStatus(int status, PageParam pageParam);

    OrderVO submitOrder(Long orderId);

    OrderVO sendOrder(Long orderId);

    OrderVO closeOrder(Long orderId);

    OrderVO cancelOrder(Long orderId);
}
