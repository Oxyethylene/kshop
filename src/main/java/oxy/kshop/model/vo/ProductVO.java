package oxy.kshop.model.vo;

import oxy.kshop.model.entity.SkuDO;

import java.util.List;

public class ProductVO {
    private Long productId;
    private String name;
    private String description;
    private String sp;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    @Override
    public String toString() {
        return "ProductVO{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sp='" + sp + '\'' +
                '}';
    }
}
