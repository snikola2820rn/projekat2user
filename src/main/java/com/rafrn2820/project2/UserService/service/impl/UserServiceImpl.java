package com.rafrn2820.project2.UserService.service.impl;

import com.rafrn2820.project2.UserService.domain.Client;
import com.rafrn2820.project2.UserService.domain.Manager;
import com.rafrn2820.project2.UserService.domain.User;
import com.rafrn2820.project2.UserService.dto.*;
import com.rafrn2820.project2.UserService.exception.NotFoundException;
import com.rafrn2820.project2.UserService.exception.WrongPasswordException;
import com.rafrn2820.project2.UserService.mapper.ClientMapper;
import com.rafrn2820.project2.UserService.mapper.ManagerMapper;
import com.rafrn2820.project2.UserService.repository.ClientRepository;
import com.rafrn2820.project2.UserService.repository.DiscountRepository;
import com.rafrn2820.project2.UserService.repository.ManagerRepository;
import com.rafrn2820.project2.UserService.security.service.TokenService;
import com.rafrn2820.project2.UserService.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public UserServiceImpl(TokenService tokenService, ClientRepository clientRepository, ManagerRepository managerRepository,
                           DiscountRepository discountRepository, ClientMapper clientMapper, ManagerMapper managerMapper) {
        this.tokenService = tokenService;
        this.clientRepository = clientRepository;
        this.managerRepository = managerRepository;
        this.discountRepository = discountRepository;
        this.clientMapper = clientMapper;
        this.managerMapper = managerMapper;
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
            client.setEmail(clientEditDto.getFirstName());
        if(clientEditDto.getLastName() != null)
            client.setEmail(clientEditDto.getLastName());
        if(clientEditDto.getPassport() != null)
            client.setEmail(clientEditDto.getPassport());
        if(clientEditDto.getPhone() != null)
            client.setEmail(clientEditDto.getPhone());
        if(clientEditDto.getUsername() != null)
            client.setEmail(clientEditDto.getUsername());
        if(clientEditDto.getPassword() != null)
            client.setEmail(clientEditDto.getPassword());
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
            manager.setEmail(managerEditDto.getFirstName());
        if(managerEditDto.getLastName() != null)
            manager.setEmail(managerEditDto.getLastName());
        if(managerEditDto.getCompany() != null)
            manager.setEmail(managerEditDto.getCompany());
        if(managerEditDto.getEmployment() != null)
            manager.setEmail(managerEditDto.getEmployment());
        if(managerEditDto.getPhone() != null)
            manager.setEmail(managerEditDto.getPhone());
        if(managerEditDto.getUsername() != null)
            manager.setEmail(managerEditDto.getUsername());
        if(managerEditDto.getPassword() != null)
            manager.setEmail(managerEditDto.getPassword());
        managerRepository.save(manager);
        return managerMapper.userToUserDto(manager);
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return (Page<UserDto>) clientRepository.findAll(pageable)
                .map(clientMapper::userToUserDto)
                .and(managerRepository.findAll(pageable).map(managerMapper::userToUserDto));
    }

    @Override
    public DiscountDto findDiscount(Long id) {
        Client client = clientRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with id: %d not found.", id)));
        Integer discount = discountRepository
                .findDiscountByTime(client.getTime())
                .getDiscount();
        return new DiscountDto(discount);
        //TODO: da li da napravim da korisnik cuva discount uvek asdsssss
    }

    @Override
    public UserDto addClient(ClientCreateDto clientCreateDto) {
        Client client = clientMapper.clientCreateDtoToClient(clientCreateDto);
        clientRepository.save(client);
        return clientMapper.userToUserDto(client);
    }

    @Override
    public UserDto addManager(ManagerCreateDto managerCreateDto) {
        Manager manager = managerMapper.managerCreateDtoToManager(managerCreateDto);
        managerRepository.save(manager);
        return managerMapper.userToUserDto(manager);
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        User user;
        Optional<Client> client = clientRepository
                .findClientByUsername(tokenRequestDto.getUsername());
        if(!client.isPresent())
            user = managerRepository
                    .findManagerByUsername(tokenRequestDto.getUsername())
                    .orElseThrow(() -> new NotFoundException(String
                            .format("User with username: %s not found.", tokenRequestDto.getUsername())));
        else
            user = client.get();
        if(!user.getPassword().equals(tokenRequestDto.getPassword()))
            throw new WrongPasswordException(String.format("Wrong password for username %s.",tokenRequestDto.getUsername()));
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("role", user.getRole());
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public UserDto findUser(Long id) {
        Optional<Client> client = clientRepository
                .findById(id);
        if(!client.isPresent())
            return managerMapper.userToUserDto(managerRepository
                    .findById(id)
                    .orElseThrow(() -> new NotFoundException(String
                            .format("User with id: %l not found.", id))));
        else
            return clientMapper.userToUserDto(client.get());
    }

    @Override
    public UserDto banUser(Long id) {
        Optional<Client> client = clientRepository
                .findById(id);
        if(!client.isPresent())
            return managerMapper.userToUserDto(managerRepository
                    .findById(id)
                    .orElseThrow(() -> new NotFoundException(String
                            .format("User with id: %l not found.", id))));
        else
            return clientMapper.userToUserDto(client.get());
    }
}
