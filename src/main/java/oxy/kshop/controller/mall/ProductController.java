package oxy.kshop.controller.mall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public List<ProductVO> getProductList() {
        return productService.getProductList();
    }
}
