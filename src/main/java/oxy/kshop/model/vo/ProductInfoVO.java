package oxy.kshop.model.vo;

import oxy.kshop.model.entity.SkuDO;

import java.util.List;

public class ProductInfoVO {
    private String sp;
    private String discount;
    private List<SkuVO> sku;

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public List<SkuVO> getSku() {
        return sku;
    }

    public void setSku(List<SkuVO> sku) {
        this.sku = sku;
    }

    @Override
    public String toString() {
        return "ProductInfoVO{" +
                "sp='" + sp + '\'' +
                ", discount='" + discount + '\'' +
                ", sku=" + sku +
                '}';
    }
}
