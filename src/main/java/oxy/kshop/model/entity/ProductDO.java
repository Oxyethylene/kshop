package oxy.kshop.model.entity;

public class ProductDO extends BaseDO{
    private String name;
    private Integer discountId;
    private String description;
    // Standard Product Unit
    private String spData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpData() {
        return spData;
    }

    public void setSpData(String spData) {
        this.spData = spData;
    }

    @Override
    public String toString() {
        return "ProductDO{" +
                "name='" + name + '\'' +
                ", discountId=" + discountId +
                ", description='" + description + '\'' +
                ", spData='" + spData + '\'' +
                '}';
    }
}
