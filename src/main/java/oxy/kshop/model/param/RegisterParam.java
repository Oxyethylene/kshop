package oxy.kshop.model.param;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterParam {
    @NotNull
    @Size(min = 3, max = 20, message = "name size should between 3 - 20")
    private String name;

    @NotNull
    @Email(message = "require valid email")
    private String email;

    @NotNull
    @Size(min = 6, max = 30, message = "password size should between 6 - 20")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
