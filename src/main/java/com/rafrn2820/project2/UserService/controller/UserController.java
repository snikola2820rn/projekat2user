package com.rafrn2820.project2.UserService.controller;

import com.rafrn2820.project2.UserService.dto.*;
import com.rafrn2820.project2.UserService.security.CheckId;
import com.rafrn2820.project2.UserService.security.CheckSecurity;
import com.rafrn2820.project2.UserService.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<UserDto>> getAllUsers(@RequestHeader("Authorization") String authorization,
                                                     Pageable pageable) {
        return new ResponseEntity<>(userService.findAllUsers(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<UserDto> getUser(@RequestHeader("Authorization") String authorization,@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findUser(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/discount")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<DiscountDto> getDiscount(@RequestHeader("Authorization") String authorization,@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findDiscount(id), HttpStatus.OK);
    }

    @PostMapping("/edit/client")
    @CheckSecurity(roles = {"ROLE_ADMIN","ROLE_CLIENT"})
    @CheckId
    public ResponseEntity<UserDto> editClient(@RequestHeader("Authorization") String authorization,@RequestBody @Valid ClientEditDto clientEditDto) {
        return new ResponseEntity<>(userService.updateClient(clientEditDto), HttpStatus.OK);
    }

    @PostMapping("/edit/manager")
    @CheckSecurity(roles = {"ROLE_ADMIN","ROLE_MANAGER"})
    @CheckId
    public ResponseEntity<UserDto> editManager(@RequestHeader("Authorization") String authorization,@RequestBody @Valid ManagerEditDto managerEditDto) {
        return new ResponseEntity<>(userService.updateManager(managerEditDto), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(userService.login(tokenRequestDto), HttpStatus.OK);
    }

    @PostMapping("/register/client")
    public ResponseEntity<UserDto> saveClient(@RequestBody @Valid ClientCreateDto clientCreateDto) {
        return new ResponseEntity<>(userService.addClient(clientCreateDto), HttpStatus.CREATED);
    }

    @PostMapping("/register/manager")
    public ResponseEntity<UserDto> saveManager(@RequestBody @Valid ManagerCreateDto managerCreateDto) {
        return new ResponseEntity<>(userService.addManager(managerCreateDto), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/ban")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<UserDto> banUser(@RequestHeader("Authorization") String authorization,@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.toggleBan(id), HttpStatus.OK);
    }


}
