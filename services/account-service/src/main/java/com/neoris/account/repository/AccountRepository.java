package com.neoris.account.repository;

import com.neoris.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Modifying
    @Query("UPDATE Account a SET a.active = false WHERE a.id = :id")
    void inactivateAccount(@Param("id") Integer id);

    @Modifying
    @Query("UPDATE Account a SET a.balance = :balance WHERE a.id = :id")
    void updateBalance(@Param("balance") BigDecimal balance, @Param("id") Integer id);

    boolean existsByIdAndActiveTrue(Integer id);

    boolean existsByAccountNumber(String accountNumber);

    List<Account> findByCustomerId(Integer clientId);

}
