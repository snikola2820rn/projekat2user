package com.rafrn2820.project2.UserService.repository;

import com.rafrn2820.project2.UserService.domain.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiscountRepository extends JpaRepository<Discount,Long> {
    @Query(value = "SELECT * FROM  users.discount WHERE minTime <= ?1", nativeQuery = true)
    Discount findDiscountByTime(Long time);
}
