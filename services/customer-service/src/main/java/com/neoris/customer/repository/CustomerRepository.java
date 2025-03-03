package com.neoris.customer.repository;

import com.neoris.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Modifying
    @Query("UPDATE Customer c SET c.active = false WHERE c.id = :id")
    void inactivateClient(@Param("id") Integer id);

    Optional<Customer> findByIdAndActiveTrue(Integer id);

    boolean existsByIdAndActiveTrue(Integer id);

}
