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
import oxy.kshop.model.param.PageParam;
import oxy.kshop.model.result.PageResult;
import oxy.kshop.model.vo.OrderVO;
import oxy.kshop.model.vo.SkuVO;
import oxy.kshop.service.IOrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements IOrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    SkuRepository skuRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public PageResult<OrderVO> getOrdersByStatus(int status, PageParam pageParam) {
        List<OrderVO> orderVOList;
        int total;
        if (status == -1) {
            final List<OrderDO> orderDOS = orderRepository.selectAllByUserId(getCurrentUserId(),
                    (pageParam.getPage() - 1) * pageParam.getSize(), pageParam.getSize());
            log.debug("orderDO {}", orderDOS);
            orderVOList = orderDOS
                    .stream()
                    .map(this::orderDO2VO)
                    .collect(Collectors.toList());
            total = orderRepository.countOrderByUserId(getCurrentUserId());
        } else {
            orderVOList = orderRepository.selectAllByUserIdAndStatus(getCurrentUserId(), status,
                    (pageParam.getPage() - 1) * pageParam.getSize(), pageParam.getSize())
                    .stream()
                    .map(this::orderDO2VO)
                    .collect(Collectors.toList());
            total = orderRepository.countOrderByUserIdAndStatus(getCurrentUserId(), status);
        }
        PageResult<OrderVO> pageResult = new PageResult<>();
        pageResult.setTotal(total);
        pageResult.setData(orderVOList);
        pageResult.setPage(pageParam.getPage());
        pageResult.setSize(pageParam.getSize());

        return pageResult;
    }

    @Override
    public OrderVO submitOrder(Long orderId) {
        final OrderDO orderDO = orderRepository.selectOrderByOrderId(orderId);
        if (orderDO.getStatus() != 0) {
            throw new ApiException("??????????????????");
        }
        if (notEditableOrder(orderId, orderDO)) {
            throw new ApiException("????????????????????????");
        }
        final SkuVO skuVO = skuRepository.selectSkuBySkuId(orderDO.getSkuId());
        // ????????????????????????
        final int newStock = skuVO.getStock() - orderDO.getCount();
        if (newStock < 0) {
            throw new ApiException("????????????");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", orderDO.getSkuId());
        map.put("stock", newStock);
        // ???????????????????????????
        skuRepository.updateSku(map);
        orderRepository.updateOrderStatusByOrderId(orderId, 1);
        orderDO.setStatus(1);
        return orderDO2VO(orderDO);
    }

    @Override
    public OrderVO sendOrder(Long orderId) {
        final OrderDO orderDO = orderRepository.selectOrderByOrderId(orderId);
        if (orderDO.getStatus() != 1) {
            throw new ApiException("??????????????????");
        }
        if (notEditableOrder(orderId, orderDO)) {
            throw new ApiException("????????????????????????");
        }
        orderRepository.updateOrderStatusByOrderId(orderId, 2);
        orderDO.setStatus(2);
        return orderDO2VO(orderDO);
    }

    @Override
    public OrderVO closeOrder(Long orderId) {
        final OrderDO orderDO = orderRepository.selectOrderByOrderId(orderId);
        if (orderDO.getStatus() != 2) {
            throw new ApiException("??????????????????");
        }
        if (notEditableOrder(orderId, orderDO)) {
            throw new ApiException("????????????????????????");
        }
        orderDO.setStatus(3);
        return orderDO2VO(orderDO);
    }

    @Override
    public OrderVO cancelOrder(Long orderId) {
        final OrderDO orderDO = orderRepository.selectOrderByOrderId(orderId);
        if (orderDO.getStatus() == 4) {
            throw new ApiException("????????????????????????");
        }
        if (notEditableOrder(orderId, orderDO)) {
            throw new ApiException("????????????????????????");
        }
        final SkuVO skuVO = skuRepository.selectSkuBySkuId(orderDO.getSkuId());
        // ????????????????????????
        final int newStock = skuVO.getStock() + orderDO.getCount();
        Map<String, Object> map = new HashMap<>();
        map.put("id", orderDO.getSkuId());
        map.put("stock", newStock);
        // ???????????????????????????
        skuRepository.updateSku(map);
        orderRepository.updateOrderStatusByOrderId(orderId, 4);
        orderDO.setStatus(4);
        return orderDO2VO(orderDO);
    }

    /**
     * ????????????????????????????????????????????????
     *
     * @param orderId ????????????
     * @return ????????????
     */
    private boolean notEditableOrder(Long orderId, OrderDO orderDO) {
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
     * ????????????DO?????????VO
     *
     * @param orderDO ?????????????????????
     * @return ??????VO
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
     * ?????????????????????????????????ID
     *
     * @return ?????????????????????ID
     */
    private Long getCurrentUserId() {
        return ((UserDetail) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal())
                .getUserDO().getId();
    }
}
