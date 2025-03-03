package com.neoris.account.service;



import com.neoris.account.domain.Account;
import com.neoris.account.dto.AccountDto;
import com.neoris.account.dto.AccountResponseDto;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    AccountResponseDto save(AccountDto requestAccount) throws Exception;
    void updateBalance(BigDecimal actualBalance, Integer id);
    void delete(Integer id);
    AccountResponseDto findById(Integer id);
    List<AccountResponseDto> findAll();
    Account validateAccount(Integer id);
    List<Account> findByCustomerId(Integer customerId);
}
