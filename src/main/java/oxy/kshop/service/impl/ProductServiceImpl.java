package oxy.kshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oxy.kshop.mapper.DiscountRepository;
import oxy.kshop.mapper.ProductRepository;
import oxy.kshop.mapper.SkuRepository;
import oxy.kshop.model.entity.DiscountDO;
import oxy.kshop.model.entity.ProductDO;
import oxy.kshop.model.vo.ProductInfoVO;
import oxy.kshop.model.vo.ProductVO;
import oxy.kshop.model.vo.SkuVO;
import oxy.kshop.service.IProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    SkuRepository skuRepository;

    @Autowired
    DiscountRepository discountRepository;

    @Override
    public List<ProductVO> getProductList() {
        final List<ProductVO> productVOList = productRepository.selectAll();
        return productVOList;
    }

    @Override
    public void addProduct() {

    }

    @Override
    public ProductInfoVO getProductInfoById(Long id) {
        final ProductInfoVO productInfoVO = new ProductInfoVO();
        final ProductDO product = productRepository.findProductById(id);
        productInfoVO.setSp(product.getSpData());
        final List<SkuVO> skuVOS = skuRepository.selectAllSkuByProductId(id);
        productInfoVO.setSku(skuVOS);
        final DiscountDO discountDO = discountRepository.selectDiscountByProductId(id);
        String discount = discountDO.getType() == 1 ? "-" + discountDO.getNum() : discountDO.getNum() + "%";
        productInfoVO.setDiscount(discount);

        return productInfoVO;
    }
}
