package oxy.kshop.service;

import oxy.kshop.model.vo.ProductInfoVO;
import oxy.kshop.model.vo.ProductVO;

import java.util.List;

public interface IProductService {
    List<ProductVO> getProductList();

    void addProduct();

    ProductInfoVO getProductInfoById(Long id);
}
