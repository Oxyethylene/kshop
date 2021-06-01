package oxy.kshop.model.vo;

public class SkuVO {
    private Long id;
    private Integer stock;
    private Double price;
    private String type;
    private String picture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "SkuVO{" +
                "id=" + id +
                ", stock=" + stock +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
