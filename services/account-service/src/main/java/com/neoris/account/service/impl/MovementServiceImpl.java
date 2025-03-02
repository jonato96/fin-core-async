package com.neoris.account.service.impl;

import com.neoris.account.domain.Account;
import com.neoris.account.domain.Movement;
import com.neoris.account.dto.MovementDto;
import com.neoris.account.dto.MovementResponseDto;
import com.neoris.account.helper.MovementType;
import com.neoris.account.mapper.MovementMapper;
import com.neoris.account.repository.MovementRepository;
import com.neoris.account.service.AccountService;
import com.neoris.account.service.MovementService;
import com.neoris.commons.library.exception.FinCoreException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService {

    private final MovementRepository movementRepository;
    private final MovementMapper movementMapper;
    private final AccountService accountService;

    @Override
    @Transactional
    public MovementResponseDto save (MovementDto movementRequest) {
        Account accountForTx =  accountService.validateAccount(movementRequest.accountId());
        validateValue(movementRequest, accountForTx.getBalance());

        BigDecimal actualBalance = BigDecimal.ZERO;
        if ( movementRequest.type() == MovementType.CREDIT ) {
            actualBalance = accountForTx.getBalance().add(movementRequest.amount());
        } else {
            actualBalance = accountForTx.getBalance().subtract(movementRequest.amount());
        }

        Movement movement = movementMapper.toTransaction(movementRequest);
        movement.setBeforeBalance(accountForTx.getBalance());
        movement.setAfterBalance(actualBalance);
        movement.setDate(LocalDate.now());

        accountService.updateBalance(actualBalance, accountForTx.getId());
        return movementMapper.toResponseDto(movementRepository.save(movement));
    }

    @Override
    @Transactional(readOnly = true)
    public MovementResponseDto findById(Integer id) {
        Movement movementFind = movementRepository.findById(id)
                .orElseThrow( () -> new FinCoreException("Movement not found with id: " + id));
        return movementMapper.toResponseDto(movementFind);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovementResponseDto> findByAccount(String account) {
        List<Movement> transactionList = movementRepository.findAllByAccount(account);
        if ( transactionList.isEmpty() ) throw new FinCoreException("Transactions with account number: " + account + " not found");
        return movementMapper.toListResponseDto(transactionList);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movement> findByAccountAndDates(Integer accountId, LocalDate start, LocalDate end) {
        return movementRepository.findByAccountIdAndDateBetween(accountId, start, end);
    }

    private void validateExistsAndIsActive(Integer id) {
        boolean result = movementRepository.existsById(id);
        if (!result) throw new FinCoreException("Transaction not found with id: " + id + ", or is already inactive");
    }

    private void validateValue(MovementDto dto, BigDecimal actualBalance) {
        String msg = "";

        if ( dto.amount().compareTo(BigDecimal.ZERO) < 0 )
            msg = "Transactions only support unsigned numbers.";

        if ( (dto.type().equals(MovementType.DEBIT) || dto.type().equals(MovementType.CREDIT))
                && dto.amount().compareTo(BigDecimal.ZERO) == 0 )
            msg = "Transaction with zero amount is not valid.";

        if ( dto.type().equals(MovementType.DEBIT) && actualBalance.compareTo(dto.amount()) < 0 )
            msg = "Account Balance is not sufficient.";

        if ( !msg.isEmpty() ) throw new FinCoreException(msg);
    }
}
