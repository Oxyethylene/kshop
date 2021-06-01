package oxy.kshop.controller.mall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import oxy.kshop.model.result.PageResult;
import oxy.kshop.model.vo.ProductInfoVO;
import oxy.kshop.model.vo.ProductVO;
import oxy.kshop.service.IProductService;
import oxy.kshop.service.impl.ProductServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    IProductService productService;

    @GetMapping("/info/{id}")
    public ProductInfoVO getProductInfo(@PathVariable Long id) {
        return productService.getProductInfoById(id);
    }

    @GetMapping("/list")
    public PageResult<ProductVO> getProductList(@RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        return productService.getProductList(page, size);
    }
}
