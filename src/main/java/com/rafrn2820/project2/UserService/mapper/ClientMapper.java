package com.rafrn2820.project2.UserService.mapper;

import com.rafrn2820.project2.UserService.domain.Client;
import com.rafrn2820.project2.UserService.dto.ClientCreateDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ClientMapper extends UserMapper {

    public Client clientCreateDtoToClient(ClientCreateDto clientCreateDto)
    {
        Client client = new Client();
        client.setFirstName(clientCreateDto.getFirstName());
        client.setLastName(clientCreateDto.getLastName());
        client.setEmail(clientCreateDto.getEmail());
        client.setTime(0);
        client.setPassport(clientCreateDto.getPassport());
        client.setPassword(clientCreateDto.getPassword());
        client.setBirthdate(LocalDate.parse(clientCreateDto.getBirthdate()));
        client.setRole("ROLE_CLIENT");
        client.setPhone(clientCreateDto.getPhone());
        return client;
    }
}
