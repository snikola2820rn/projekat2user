package com.rafrn2820.project2.UserService.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class ClientCreateDto extends UserCreateDto{
    @NotBlank
    @Length(min = 8, max = 10)
    private String passport;

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}
