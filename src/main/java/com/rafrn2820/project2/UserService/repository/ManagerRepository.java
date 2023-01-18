package com.rafrn2820.project2.UserService.repository;

import com.rafrn2820.project2.UserService.domain.Client;
import com.rafrn2820.project2.UserService.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Long> {
    Optional<Manager> findManagerByUsernameAndPassword(String username, String password);

    Optional<Manager> findManagerByUsername(String username);

}
