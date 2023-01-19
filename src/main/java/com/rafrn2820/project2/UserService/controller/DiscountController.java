package com.rafrn2820.project2.UserService.controller;

import com.rafrn2820.project2.UserService.dto.DiscountCreateDto;
import com.rafrn2820.project2.UserService.dto.DiscountDto;
import com.rafrn2820.project2.UserService.security.CheckSecurity;
import com.rafrn2820.project2.UserService.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discount")
public class DiscountController {

    private UserService userService;

    public DiscountController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<DiscountDto> addDiscount(@RequestHeader("Authorization") String authorization, @RequestBody @Valid DiscountCreateDto discountCreateDto) {
        return new ResponseEntity<>(userService.addDiscount(discountCreateDto), HttpStatus.OK);
    }

    @PostMapping("/edit")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<DiscountDto> editDiscount(@RequestHeader("Authorization") String authorization,@RequestBody @Valid DiscountDto discountDto) {
        return new ResponseEntity<>(userService.editDiscount(discountDto), HttpStatus.OK);
    }
}
