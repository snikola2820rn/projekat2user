package com.rafrn2820.project2.UserService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class ClientEditDto extends UserEditDto{
    @Length(min = 8, max = 10)
    private String passport;

    public String getPassport() {
        return passport;
    }
}
