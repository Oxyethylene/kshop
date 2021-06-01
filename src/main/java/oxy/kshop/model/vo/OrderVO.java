package oxy.kshop.model.vo;

public class OrderVO {
    private long orderId;
    private String productName;
    private String productPicture;
    private Integer count;
    private Double price;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OderVO{" +
                "orderId=" + orderId +
                ", productName='" + productName + '\'' +
                ", productPicture='" + productPicture + '\'' +
                ", count='" + count + '\'' +
                ", price=" + price +
                '}';
    }
}
