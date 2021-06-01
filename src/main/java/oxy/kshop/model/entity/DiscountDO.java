package oxy.kshop.model.entity;

public class DiscountDO extends BaseDO {
    private Integer type;
    private Integer num;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "DiscountDO{" +
                "type=" + type +
                ", num=" + num +
                '}';
    }
}
