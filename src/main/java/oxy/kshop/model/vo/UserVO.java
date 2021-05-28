package oxy.kshop.model.vo;

import java.util.Set;

/**
 * @author kudlife
 */
public class UserVO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 登陆认证token
     */
    private String token;

    /**
     * 用户权限集合
     */
    private Set<String> resources;

    public UserVO() {
    }

    public UserVO(Long id, String name, String token, Set<String> resources) {
        this.id = id;
        this.name = name;
        this.token = token;
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", resources=" + resources +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<String> getResources() {
        return resources;
    }

    public void setResources(Set<String> resources) {
        this.resources = resources;
    }

}
