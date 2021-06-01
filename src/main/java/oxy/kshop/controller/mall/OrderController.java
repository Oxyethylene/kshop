package oxy.kshop.controller.mall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import oxy.kshop.model.param.PageParam;
import oxy.kshop.model.result.PageResult;
import oxy.kshop.model.vo.OrderVO;
import oxy.kshop.service.IOrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    IOrderService orderService;

    @GetMapping
    public PageResult<OrderVO> getOrders(@RequestParam(defaultValue = "-1") int status,
                                         @RequestParam(defaultValue = "1,10") PageParam pageparam) {
        return orderService.getOrdersByStatus(status, pageparam);
    }

    @PutMapping("/submit/{orderId}")
    public OrderVO submitOrder(@PathVariable Long orderId) {
        return orderService.submitOrder(orderId);
    }

    @PutMapping("/send/{orderId}")
    public OrderVO sendOrder(@PathVariable Long orderId) {
        return orderService.sendOrder(orderId);
    }

    @PutMapping("/close/{orderId}")
    public OrderVO closeLOrder(@PathVariable Long orderId) {
        return orderService.closeOrder(orderId);
    }

    @PutMapping("/cancel/{orderId}")
    public OrderVO cancelOrder(@PathVariable Long orderId) {
        return orderService.cancelOrder(orderId);
    }
}
