package oxy.kshop.model.param;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author kudlife
 */
public class LoginParam {
    @NotNull(message = "email is required")
    @Email(message = "should be valid email")
    private String email;

    @NotNull(message = "password is required")
    @Size(min = 6, max = 20, message = "password should between 6 - 20")
    private String password;

    public LoginParam(String email, String password) {
        this.email = email;
        this.password = password;
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
