package com.rafrn2820.project2.UserService.service;

import com.rafrn2820.project2.UserService.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserDto> findAll(Pageable pageable);
    DiscountDto findDiscount(Long id);

    UserDto addClient(ClientCreateDto clientCreateDto);

    UserDto addManager(ManagerCreateDto clientCreateDto);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    UserDto updateClient(ClientEditDto clientEditDto);

    UserDto updateManager(ManagerEditDto managerEditDto);

    UserDto findUser(Long id);

    UserDto banUser(Long id);

}
