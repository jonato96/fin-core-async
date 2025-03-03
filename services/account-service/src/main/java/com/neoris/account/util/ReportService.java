package com.neoris.account.util;

import com.neoris.account.domain.Account;
import com.neoris.account.domain.Movement;
import com.neoris.account.dto.ReportResponseDto;
import com.neoris.account.dto.client.CustomerResponseDto;
import com.neoris.account.mapper.MovementMapper;
import com.neoris.account.rabbit.CustomerRequestProducer;
import com.neoris.account.service.AccountService;
import com.neoris.account.service.MovementService;
import com.neoris.commons.library.exception.FinCoreException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final AccountService accountService;
    private final MovementService movementService;
    private final MovementMapper movementMapper;
    private final CustomerRequestProducer customerProducer;

    public List<ReportResponseDto> generateReport(Integer customerId, LocalDate startDate, LocalDate endDate) {

        if ( endDate.isBefore(startDate) ) throw new FinCoreException("Range date is incorrect.");

        CustomerResponseDto clientFind = customerProducer.findCustomer(customerId);
        if(Objects.isNull(clientFind) || !clientFind.active() ) throw new FinCoreException("Client is not active.");

        List<Account> accounts = accountService.findByCustomerId(customerId);
        if (accounts.isEmpty() ) throw new FinCoreException("Client does not have any account.");
        return accounts.stream()
            .map(account -> {
                List<Movement> transactions = movementService.findByAccountAndDates(account.getId(), startDate, endDate);
                return new ReportResponseDto(
                        clientFind.name(),
                        account.getAccountNumber(),
                        account.getType().toString(),
                        account.getBalance(),
                        movementMapper.toReportDto(transactions) );
            })
            .toList();

    }

}
