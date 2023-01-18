package com.rafrn2820.project2.UserService.mapper;

import com.rafrn2820.project2.UserService.domain.User;
import com.rafrn2820.project2.UserService.dto.UserDto;

public abstract class UserMapper {
    public UserDto userToUserDto(User user)
    {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setRole(userDto.getRole());
        userDto.set
        return userDto;
    }
}
