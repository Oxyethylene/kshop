package oxy.kshop.model.entity;

public class SkuDO extends BaseDO{
    private Long productId;
    private Integer Stock;
    private Double price;
    private String type;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getStock() {
        return Stock;
    }

    public void setStock(Integer stock) {
        Stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SkuDO{" +
                "productId=" + productId +
                ", Stock=" + Stock +
                ", price=" + price +
                ", type='" + type + '\'' +
                '}';
    }
}
