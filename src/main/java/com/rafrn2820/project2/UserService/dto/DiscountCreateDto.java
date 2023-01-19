package com.rafrn2820.project2.UserService.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DiscountCreateDto {
    @NotNull
    @Min(value = 0)
    private Long discount;
    @NotNull
    private String title;
    @NotNull
    @Min(value = 0)
    private Long minTime;
    @NotNull
    private Long maxTime;


    public DiscountCreateDto() {
    }

    public Long getDiscount() {
        return discount;
    }

    public Long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Long maxTime) {
        this.maxTime = maxTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getMinTime() {
        return minTime;
    }

    public void setMinTime(Long minTime) {
        this.minTime = minTime;
    }

    public DiscountCreateDto(Long discount) {
        this.discount = discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }
}
