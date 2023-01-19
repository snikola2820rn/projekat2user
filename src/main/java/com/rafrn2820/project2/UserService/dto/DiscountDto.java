package com.rafrn2820.project2.UserService.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class DiscountDto {
    @NotNull
    private Long id;
    @Min(value = 0)
    private Long discount;
    @Length(min = 1)
    private String title;
    @Min(value = 0)
    private Long minTime;
    @Min(value = 0)
    private Long maxTime;
    public Long getDiscount() {
        return discount;
    }

    public DiscountDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Long maxTime) {
        this.maxTime = maxTime;
    }

    public Long getMinTime() {
        return minTime;
    }

    public void setMinTime(Long minTime) {
        this.minTime = minTime;
    }

    public DiscountDto(Long discount) {
        this.discount = discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }
}
