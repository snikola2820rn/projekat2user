package com.rafrn2820.project2.UserService.mapper;

import com.rafrn2820.project2.UserService.domain.Manager;
import com.rafrn2820.project2.UserService.dto.ManagerCreateDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ManagerMapper extends UserMapper{
    public Manager managerCreateDtoToManager(ManagerCreateDto managerCreateDto)
    {
        Manager manager = new Manager();
        manager.setFirstName(managerCreateDto.getFirstName());
        manager.setLastName(managerCreateDto.getLastName());
        manager.setEmail(managerCreateDto.getEmail());
        manager.setCompany(managerCreateDto.getCompany());
        manager.setEmployment(LocalDate.parse(managerCreateDto.getEmployment()));
        manager.setPassword(managerCreateDto.getPassword());
        manager.setBirthdate(LocalDate.parse(managerCreateDto.getBirthdate()));
        manager.setRole("ROLE_MANAGER");
        manager.setPhone(managerCreateDto.getPhone());
        return manager;
    }
}
