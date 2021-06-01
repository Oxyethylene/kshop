package oxy.kshop.controller.mall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import oxy.kshop.config.security.UserDetail;
import oxy.kshop.model.entity.UserDO;
import oxy.kshop.model.result.PageResult;
import oxy.kshop.model.vo.OrderVO;
import oxy.kshop.service.ICartService;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @GetMapping
    public PageResult<OrderVO> getCart(@RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        UserDetail principal = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = principal.getUserDO().getId();
        return cartService.getCartByUserId(userId, page, size);
    }

    @PostMapping
    public OrderVO addProductToCart(@RequestParam Long skuId,
                                    @RequestParam int count) {
        final UserDO userDO =
                ((UserDetail) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal())
                        .getUserDO();
        Long userId = userDO.getId();
        return cartService.addProductToCart(userId, skuId, count);
    }

    @DeleteMapping
    public void deleteCartByOrderId(@RequestParam Long orderId) {
        cartService.deleteCartByOrderId(orderId);
    }
}
