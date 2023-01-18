package com.rafrn2820.project2.UserService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class UserEditDto {
    private Long id;
    @Email(regexp = "[a-zA-Z0-9]+@([a-zA-Z].)+com")
    private String email;
    @Length(min = 1)
    private String username;
    @Length(min = 1,max = 20)
    private String password;
    @Length(min = 1)
    private String firstName;
    @Length(min = 1)
    private String lastName;
    @Length(min = 1)
    //@Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$")
    private String phone;
    @Length(min = 1)
    @Pattern(regexp = "^(?:0[1-9]|[12]\\d|3[01])([\\/.-])(?:0[1-9]|1[012])\\1(?:19|20)\\d\\d$")
    private String birthdate;

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public Long getId() {
        return id;
    }
}
