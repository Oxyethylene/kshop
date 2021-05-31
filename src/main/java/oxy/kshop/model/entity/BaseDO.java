package oxy.kshop.model.entity;

public abstract class BaseDO {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseDO{" +
                "id=" + id +
                '}';
    }
}
