package com.rafrn2820.project2.UserService.mapper;

import com.rafrn2820.project2.UserService.domain.User;
import com.rafrn2820.project2.UserService.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto userToUserDto(User user)
    {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setRole(user.getRole());
        userDto.setUsername(user.getUsername());
        return userDto;
    }
}
