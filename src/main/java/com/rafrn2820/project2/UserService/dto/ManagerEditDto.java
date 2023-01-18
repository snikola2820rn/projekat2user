package com.rafrn2820.project2.UserService.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class ManagerEditDto extends UserEditDto{
    @Length(min = 1)
    private String company;
    @Length(min = 1)
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
