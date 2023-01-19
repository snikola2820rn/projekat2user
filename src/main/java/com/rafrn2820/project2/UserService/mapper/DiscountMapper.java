package com.rafrn2820.project2.UserService.mapper;

import com.rafrn2820.project2.UserService.domain.Discount;
import com.rafrn2820.project2.UserService.dto.DiscountCreateDto;
import com.rafrn2820.project2.UserService.dto.DiscountDto;
import org.springframework.stereotype.Component;

@Component
public class DiscountMapper {
    public Discount discountDtoToDiscount(DiscountDto discountDto)
    {
        Discount discount = new Discount();
        discount.setTitle(discountDto.getTitle());
        discount.setMinTime(discountDto.getMinTime());
        discount.setDiscount(discountDto.getDiscount());
        return discount;
    }

    public DiscountDto discountToDiscountDto(Discount discount)
    {
        DiscountDto discountDto = new DiscountDto();
        discountDto.setTitle(discount.getTitle());
        discountDto.setMinTime(discount.getMinTime());
        discountDto.setDiscount(discount.getDiscount());
        discountDto.setId(discount.getId());
        discountDto.setMaxTime(discount.getMaxTime());
        return discountDto;
    }

    public Discount discountCreateDtoToDiscount(DiscountCreateDto discountCreateDto)
    {
        Discount discount = new Discount();
        discount.setTitle(discountCreateDto.getTitle());
        discount.setMinTime(discountCreateDto.getMinTime());
        discount.setDiscount(discountCreateDto.getDiscount());
        discount.setMaxTime(discountCreateDto.getMaxTime());
        return discount;
    }
}
