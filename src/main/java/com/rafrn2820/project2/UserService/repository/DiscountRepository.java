package com.rafrn2820.project2.UserService.repository;

import com.rafrn2820.project2.UserService.domain.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount,Long> {
    @Query(value = "SELECT * FROM  users.discount WHERE users.discount.min_time <= ?1 AND users.discount.max_time >= ?1", nativeQuery = true)
    Discount findDiscountByTime(Long minTime);
}
