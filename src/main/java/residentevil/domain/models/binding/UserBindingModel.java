package residentevil.domain.models.binding;

import org.hibernate.validator.constraints.Length;
import residentevil.web.validators.FieldsEquality;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@FieldsEquality(firstFieldName = "password", secondFieldName = "confirmPassword")
public class UserBindingModel {
    private String id;
    private String username;
    private String password;
    private String confirmPassword;
    private String email;

    private boolean isAdmin;

    public UserBindingModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotEmpty
    @NotNull
    @Length(min = 3, max = 12)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty
    @NotNull
    @Length(min = 3, max=12)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty
    @NotNull
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @NotEmpty
    @NotNull
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
