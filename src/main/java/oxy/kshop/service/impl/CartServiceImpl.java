package oxy.kshop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import oxy.kshop.config.security.UserDetail;
import oxy.kshop.exception.ApiException;
import oxy.kshop.mapper.OrderRepository;
import oxy.kshop.mapper.SkuRepository;
import oxy.kshop.model.entity.OrderDO;
import oxy.kshop.model.result.PageResult;
import oxy.kshop.model.vo.OrderVO;
import oxy.kshop.model.vo.SkuVO;
import oxy.kshop.service.ICartService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class CartServiceImpl implements ICartService {
    private static final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SkuRepository skuRepository;

    @Override
    public PageResult<OrderVO> getCartByUserId(Long id, int page, int size) {
        final PageResult<OrderVO> result = new PageResult<>();
        final List<OrderDO> orderDOList = orderRepository.selectCartByUserId(id, (page - 1) * size, size);
        // 将查询出的DO转换为VO
        List<OrderVO> orderVOS =orderDOList
                .stream()
                .map(this::orderDO2VO)
                .collect(Collectors.toList());
        result.setTotal(orderRepository.selectCartSizeByUserId(id));
        result.setPage(page);
        result.setSize(size);
        result.setData(orderVOS);

        return result;
    }

    @Override
    public OrderVO addProductToCart(Long userId, Long skuId, int count) {
        // 商品sku不存在
        if (skuRepository.selectSkuBySkuId(skuId) == null) {
            throw new ApiException("商品不存在");
        }
        // 商品已在购物车
        if (orderRepository.selectCartByUserIdAndSkuId(userId, skuId) != null) {
            throw new ApiException("已在购物车，应使用其他方法");
        }
        OrderDO orderDO = new OrderDO();
        orderDO.setUserId(userId);
        orderDO.setSkuId(skuId);
        orderDO.setCount(count);
        orderRepository.save(orderDO);
        return orderDO2VO(orderDO);
    }

    @Override
    public void deleteCartByOrderId(Long id) {
        if (notEditableOrder(id)) {
            return;
        }
        orderRepository.deleteCartByOrderId(id);
    }

    @Override
    public OrderVO changeCartCount(Long orderId, int count) {
        if (count <= 0 || count > 99) {
            throw new ApiException("单个订单数量应在1 - 99之间");
        }
        if (notEditableOrder(orderId)) {
            throw new ApiException("无法修改该订单");
        }
        orderRepository.updateOrderCountByOrderId(orderId, count);
        return orderDO2VO(orderRepository.selectCartByOrderId(orderId));
    }

    /**
     * 判断订单是否存在且为当前用户所有
     * @param orderId 订单编号
     * @return 判断结果
     */
    private boolean notEditableOrder(Long orderId) {
        OrderDO orderDO = orderRepository.selectCartByOrderId(orderId);
        if (orderDO == null || !orderDO.getUserId().equals(getCurrentUserId())) {
            if (orderDO == null) {
                log.info("order '{}' not exist, bypass", orderId);
            } else {
                log.info("order '{}' owner is user '{}', not belong to user '{}', bypass",
                        orderId, orderDO.getUserId(), getCurrentUserId());
            }
            return true;
        }
        return false;
    }

    /**
     * 将订单的DO转换为VO
     * @param orderDO 要被展示的订单
     * @return 订单VO
     */
    private OrderVO orderDO2VO(OrderDO orderDO) {
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderId(orderDO.getId());
        orderVO.setCount(orderDO.getCount());
        SkuVO sku = skuRepository.selectSkuBySkuId(orderDO.getSkuId());
        orderVO.setPrice(sku.getPrice() * orderDO.getCount());
        orderVO.setProductPicture(sku.getPicture());
        orderVO.setProductName(skuRepository.selectProductNameBySkuId(sku.getId()));
        return orderVO;
    }

    /**
     * 获取当前登陆用户的用户ID
     * @return 当前访问用户的ID
     */
    private Long getCurrentUserId() {
        return ((UserDetail) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal())
                .getUserDO().getId();
    }

}
