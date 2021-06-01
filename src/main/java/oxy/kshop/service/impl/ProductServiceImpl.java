package oxy.kshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import oxy.kshop.exception.ApiException;
import oxy.kshop.mapper.DiscountRepository;
import oxy.kshop.mapper.ProductRepository;
import oxy.kshop.mapper.SkuRepository;
import oxy.kshop.model.entity.DiscountDO;
import oxy.kshop.model.entity.ProductDO;
import oxy.kshop.model.result.PageResult;
import oxy.kshop.model.vo.ProductInfoVO;
import oxy.kshop.model.vo.ProductVO;
import oxy.kshop.model.vo.SkuVO;
import oxy.kshop.service.IProductService;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    SkuRepository skuRepository;

    @Autowired
    DiscountRepository discountRepository;

    @Override
    public PageResult<ProductVO> getProductList(int page, int size) {
        final PageResult<ProductVO> result = new PageResult<>();
        final List<ProductVO> productVOS = productRepository.selectAll((page - 1) * size, size);
        result.setData(productVOS);
        result.setPage(page);
        result.setSize(size);
        result.setTotal(productRepository.count());

        return result;
    }

    @Override
    public void addProduct() {

    }

    @Override
    public ProductInfoVO getProductInfoById(Long id) {
        final ProductInfoVO productInfoVO = new ProductInfoVO();
        final ProductDO product = productRepository.selectProductById(id);
        if (product == null) {
            throw new ApiException("产品ID不存在");
        }
        productInfoVO.setSp(product.getSpData());
        final List<SkuVO> skuVOS = skuRepository.selectAllSkuByProductId(id);
        productInfoVO.setSku(skuVOS);
        final DiscountDO discountDO = discountRepository.selectDiscountByProductId(id);
        String discount = discountDO.getType() == 1 ? "-" + discountDO.getNum() : discountDO.getNum() + "%";
        productInfoVO.setDiscount(discount);

        return productInfoVO;
    }
}
