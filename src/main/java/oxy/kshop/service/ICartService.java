package oxy.kshop.service;

import oxy.kshop.model.entity.OrderDO;
import oxy.kshop.model.result.PageResult;
import oxy.kshop.model.vo.OrderVO;

public interface ICartService {
    PageResult<OrderVO> getCartByUserId(Long id, int page, int size);
}
