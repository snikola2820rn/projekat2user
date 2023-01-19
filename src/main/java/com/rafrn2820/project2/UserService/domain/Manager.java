package com.rafrn2820.project2.UserService.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("ROLE_MANAGER")
public class Manager extends User{
    private String company;
    @CreatedDate
    private LocalDate employment;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LocalDate getEmployment() {
        return employment;
    }

    public void setEmployment(LocalDate employment) {
        this.employment = employment;
    }
}
