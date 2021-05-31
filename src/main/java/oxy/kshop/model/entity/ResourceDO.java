package oxy.kshop.model.entity;

public class ResourceDO extends BaseDO{
    private String path;
    private String name;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ResourceDO{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
