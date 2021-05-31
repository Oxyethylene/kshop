package oxy.kshop.model.entity;

public class RoleDO extends BaseDO{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RoleDO{" +
                "name='" + name + '\'' +
                '}';
    }
}
