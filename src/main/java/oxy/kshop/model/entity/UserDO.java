package oxy.kshop.model.entity;

import java.util.Date;

/**
 * @author kudlife
 */
public class UserDO extends BaseDO{
    private String name;
    private String password;
    private String email;
    private Date createDate;
    private Date modifiedDate;

    public UserDO(){}

    public UserDO(Long id, String name, String password, String email, Date createDate, Date modifiedDate) {
        setId(id);
        this.name = name;
        this.password = password;
        this.email = email;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", createDate=" + createDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
