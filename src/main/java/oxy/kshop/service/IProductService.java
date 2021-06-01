package oxy.kshop.service;

import oxy.kshop.model.result.PageResult;
import oxy.kshop.model.vo.ProductInfoVO;
import oxy.kshop.model.vo.ProductVO;

import java.util.List;

public interface IProductService {
    PageResult<ProductVO> getProductList(int page, int size);

    void addProduct();

    ProductInfoVO getProductInfoById(Long id);
}
