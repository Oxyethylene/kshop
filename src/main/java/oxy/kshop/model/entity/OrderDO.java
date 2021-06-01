package oxy.kshop.model.entity;

public class OrderDO extends BaseDO{
    private Long userId;
    private Long SkuId;
    private int status;
    private int count;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSkuId() {
        return SkuId;
    }

    public void setSkuId(Long skuId) {
        SkuId = skuId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "OrderDO{" +
                "userId=" + userId +
                ", SkuId=" + SkuId +
                ", status=" + status +
                ", count=" + count +
                '}';
    }
}
