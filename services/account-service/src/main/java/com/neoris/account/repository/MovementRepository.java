package com.neoris.account.repository;

import com.neoris.account.domain.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Integer> {

    @Query("select m from Movement m where m.account.accountNumber = :accountNumber")
    List<Movement> findAllByAccount(@Param("accountNumber") String accountNumber);

    List<Movement> findByAccountIdAndDateBetween(Integer accountId, LocalDate startData, LocalDate endDate);

}
