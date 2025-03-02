package com.neoris.account.dto;

import com.neoris.account.helper.AccountType;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountResponseDto (
        Integer id,
        String accountNumber,
        AccountType type,
        BigDecimal balance,
        boolean active,
        String clientName
) {}
