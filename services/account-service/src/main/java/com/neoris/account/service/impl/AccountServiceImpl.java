package com.neoris.account.service.impl;


import com.neoris.account.domain.Account;
import com.neoris.account.dto.AccountDto;
import com.neoris.account.dto.AccountResponseDto;
import com.neoris.account.dto.client.CustomerResponseDto;
import com.neoris.account.mapper.AccountMapper;
import com.neoris.account.rabbit.CustomerRequestProducer;
import com.neoris.account.repository.AccountRepository;
import com.neoris.account.service.AccountService;
import com.neoris.commons.library.exception.FinCoreException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final CustomerRequestProducer customerProducer;

    @Override
    @Transactional
    public AccountResponseDto save(AccountDto requestAccount) {
        if ( accountRepository.existsByAccountNumber(requestAccount.accountNumber()) )
            throw new FinCoreException("Account number is not valid.");
        CustomerResponseDto clientForAccount = customerProducer.findCustomer(requestAccount.customerId());
        Account accountToSave = accountMapper.toAccount(requestAccount);
        accountToSave.setActive(true);
        accountToSave.setCustomerId(clientForAccount.id());
        Account accountCreated = accountRepository.save(accountToSave);
        return accountMapper.toResponseDto(accountCreated, clientForAccount.name());
    }

    @Override
    @Transactional
    public void updateBalance(BigDecimal actualBalance, Integer id) {
        accountRepository.updateBalance(actualBalance, id);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        validateExistsAndIsActive(id);
        accountRepository.inactivateAccount(id);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountResponseDto findById(Integer id) {
        Account accountFind = accountRepository.findById(id).orElseThrow( () -> new FinCoreException("Account not found with id: " + id) );
        CustomerResponseDto clientResponse = customerProducer.findCustomer(accountFind.getCustomerId());
        return accountMapper.toResponseDto(accountFind, clientResponse.name());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponseDto> findAll() {
        return accountMapper.toResponseDtoList(accountRepository.findAll());
    }

    @Override
    public Account validateAccount(Integer id) {
        validateExistsAndIsActive(id);
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public List<Account> findByCustomerId(Integer customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

    private void validateExistsAndIsActive(Integer id) {
        boolean result = accountRepository.existsByIdAndActiveTrue(id);
        if (!result) throw new FinCoreException("Account not found or is already inactive.");
    }
}
