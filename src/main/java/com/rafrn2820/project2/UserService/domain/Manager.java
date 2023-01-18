package com.rafrn2820.project2.UserService.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Table(indexes = {@Index(columnList = "username", unique = true), @Index(columnList = "email", unique = true)})
public class Manager extends User{
    private String company;
    @CreatedDate
    @Column(name = "date_of_employment")
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
