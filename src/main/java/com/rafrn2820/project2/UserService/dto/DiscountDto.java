package com.rafrn2820.project2.UserService.dto;

public class DiscountDto {
    private Integer discount;

    public Integer getDiscount() {
        return discount;
    }

    public DiscountDto() {

    }

    public DiscountDto(Integer discount) {
        this.discount = discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
