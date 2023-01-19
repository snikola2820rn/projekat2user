package com.rafrn2820.project2.UserService.service.impl;

import com.rafrn2820.project2.UserService.domain.Client;
import com.rafrn2820.project2.UserService.domain.Discount;
import com.rafrn2820.project2.UserService.domain.Manager;
import com.rafrn2820.project2.UserService.domain.User;
import com.rafrn2820.project2.UserService.dto.*;
import com.rafrn2820.project2.UserService.exception.BannedUserException;
import com.rafrn2820.project2.UserService.exception.NotFoundException;
import com.rafrn2820.project2.UserService.exception.WrongPasswordException;
import com.rafrn2820.project2.UserService.mapper.ClientMapper;
import com.rafrn2820.project2.UserService.mapper.DiscountMapper;
import com.rafrn2820.project2.UserService.mapper.ManagerMapper;
import com.rafrn2820.project2.UserService.mapper.UserMapper;
import com.rafrn2820.project2.UserService.repository.ClientRepository;
import com.rafrn2820.project2.UserService.repository.DiscountRepository;
import com.rafrn2820.project2.UserService.repository.ManagerRepository;
import com.rafrn2820.project2.UserService.repository.UserRepository;
import com.rafrn2820.project2.UserService.security.service.TokenService;
import com.rafrn2820.project2.UserService.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private TokenService tokenService;
    private ClientRepository clientRepository;
    private ManagerRepository managerRepository;
    private DiscountRepository discountRepository;
    private ClientMapper clientMapper;
    private ManagerMapper managerMapper;
    private UserRepository userRepository;
    private UserMapper userMapper;

    private DiscountMapper discountMapper;

    public UserServiceImpl(TokenService tokenService, ClientRepository clientRepository, ManagerRepository managerRepository, UserRepository userRepository,
                           DiscountRepository discountRepository, ClientMapper clientMapper, ManagerMapper managerMapper, UserMapper userMapper, DiscountMapper discountMapper) {
        this.tokenService = tokenService;
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.managerRepository = managerRepository;
        this.discountRepository = discountRepository;
        this.clientMapper = clientMapper;
        this.managerMapper = managerMapper;
        this.userMapper = userMapper;
        this.discountMapper = discountMapper;
    }


    @Override
    public UserDto updateClient(ClientEditDto clientEditDto) {
        Client client = clientRepository
                .findById(clientEditDto.getId())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with id: %d not found.", clientEditDto.getId())));
        if(clientEditDto.getEmail() != null)
            client.setEmail(clientEditDto.getEmail());
        if(clientEditDto.getFirstName() != null)
            client.setFirstName(clientEditDto.getFirstName());
        if(clientEditDto.getLastName() != null)
            client.setLastName(clientEditDto.getLastName());
        if(clientEditDto.getPassport() != null)
            client.setPassport(clientEditDto.getPassport());
        if(clientEditDto.getPhone() != null)
            client.setPhone(clientEditDto.getPhone());
        if(clientEditDto.getUsername() != null)
            client.setUsername(clientEditDto.getUsername());
        if(clientEditDto.getPassword() != null)
            client.setPassword(clientEditDto.getPassword());
        clientRepository.save(client);
        return clientMapper.userToUserDto(client);
    }

    @Override
    public UserDto updateManager(ManagerEditDto managerEditDto) {
        Manager manager = managerRepository
                .findById(managerEditDto.getId())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with id: %d not found.", managerEditDto.getId())));
        if(managerEditDto.getEmail() != null)
            manager.setEmail(managerEditDto.getEmail());
        if(managerEditDto.getFirstName() != null)
            manager.setFirstName(managerEditDto.getFirstName());
        if(managerEditDto.getLastName() != null)
            manager.setLastName(managerEditDto.getLastName());
        if(managerEditDto.getCompany() != null)
            manager.setCompany(managerEditDto.getCompany());
        if(managerEditDto.getEmployment() != null)
            manager.setEmployment(LocalDate.parse(managerEditDto.getEmployment(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        if(managerEditDto.getPhone() != null)
            manager.setPhone(managerEditDto.getPhone());
        if(managerEditDto.getUsername() != null)
            manager.setUsername(managerEditDto.getUsername());
        if(managerEditDto.getPassword() != null)
            manager.setPassword(managerEditDto.getPassword());
        managerRepository.save(manager);
        return managerMapper.userToUserDto(manager);
    }

    @Override
    public Page<UserDto> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::userToUserDto);
    }

    @Override
    public DiscountDto findDiscount(Long id) {
        Client client = clientRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with id: %d not found.", id)));
        Discount discount = discountRepository
                .findDiscountByTime(client.getTime());
        return discountMapper.discountToDiscountDto(discount);
        //TODO: da li da napravim da korisnik cuva discount uvek
    }

    @Override
    public UserDto addClient(ClientCreateDto clientCreateDto) {
        Client client = clientMapper.clientCreateDtoToClient(clientCreateDto);
        clientRepository.save(client);
        client.setRole("ROLE_CLIENT");
        return clientMapper.userToUserDto(client);
    }

    @Override
    public UserDto addManager(ManagerCreateDto managerCreateDto) {
        Manager manager = managerMapper.managerCreateDtoToManager(managerCreateDto);
        managerRepository.save(manager);
        manager.setRole("ROLE_MANAGER");
        return managerMapper.userToUserDto(manager);
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        User user = userRepository
                .findByUsername(tokenRequestDto.getUsername())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with username: %s not found.", tokenRequestDto.getUsername())));
        if(!user.getPassword().equals(tokenRequestDto.getPassword()))
            throw new WrongPasswordException(String.format("Wrong password for username %s.",tokenRequestDto.getUsername()));
        if(user.isBanned())
            throw new BannedUserException("This user is banned");
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("role", user.getRole());
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public UserDto findUser(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with id: %s not found.", id)));
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto toggleBan(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with id: %s not found.", id)));
        user.setBanned(!user.isBanned());
        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public DiscountDto addDiscount(DiscountCreateDto discountDto) {
        Discount discount = discountMapper.discountCreateDtoToDiscount(discountDto);
        discountRepository.save(discount);
        return discountMapper.discountToDiscountDto(discount);
    }

    @Override
    public DiscountDto editDiscount(DiscountDto discountDto) {
        Discount discount = discountRepository
                .findById(discountDto.getId())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with id: %s not found.", discountDto.getId())));
        if(discountDto.getDiscount() != null)
            discount.setDiscount(discountDto.getDiscount());
        if(discountDto.getTitle() != null)
            discount.setTitle(discountDto.getTitle());
        if(discountDto.getMinTime() != null)
            discount.setMinTime(discountDto.getMinTime());
        if(discountDto.getMaxTime() != null)
            discount.setMaxTime(discountDto.getMaxTime());
        discountRepository.save(discount);
        return discountMapper.discountToDiscountDto(discount);
    }

    @Override
    public UserDto removeUser(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with id: %s not found.", id)));
        userRepository.delete(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public DiscountDto removeDiscount(Long id) {
        Discount discount = discountRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with id: %l not found.", id)));
        discountRepository.delete(discount);
        return discountMapper.discountToDiscountDto(discount);
    }
}
