package com.rafrn2820.project2.UserService.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class ManagerCreateDto extends UserCreateDto{
    @NotBlank
    private String company;
    @NotBlank
    private String employment;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }
}
