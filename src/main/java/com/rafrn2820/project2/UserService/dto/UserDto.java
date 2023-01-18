package com.rafrn2820.project2.UserService.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDto {

    private Long id;
    private String username;
    private String role;

    public Long getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
