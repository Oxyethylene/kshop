package oxy.kshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import oxy.kshop.mapper.OrderRepository;
import oxy.kshop.mapper.ProductRepository;
import oxy.kshop.mapper.SkuRepository;
import oxy.kshop.model.entity.OrderDO;
import oxy.kshop.model.result.PageResult;
import oxy.kshop.model.vo.OrderVO;
import oxy.kshop.model.vo.SkuVO;
import oxy.kshop.service.ICartService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CartServiceImpl implements ICartService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SkuRepository skuRepository;

    @Override
    public PageResult<OrderVO> getCartByUserId(Long id, int page, int size) {
        final PageResult<OrderVO> result = new PageResult<>();
        final List<OrderDO> orderDOList = orderRepository.selectCartByUserId(id, (page - 1) * size, size);
        final List<OrderVO> orderVOS = new ArrayList<>();
        // 将查询出的DO转换为VO
        for (OrderDO orderDO : orderDOList) {
            final OrderVO orderVO = new OrderVO();
            orderVO.setOrderId(orderDO.getId());
            orderVO.setCount(orderDO.getCount());
            SkuVO sku = skuRepository.selectSkuBySkuId(orderDO.getSkuId());
            orderVO.setPrice(sku.getPrice());
            orderVO.setProductPicture(sku.getPicture());
            orderVO.setProductName(skuRepository.selectProductNameBySkuId(sku.getId()));
            orderVOS.add(orderVO);
        }
        result.setTotal(orderRepository.selectCartSizeByUserId(id));
        result.setPage(page);
        result.setSize(size);
        result.setData(orderVOS);

        return result;
    }
}
