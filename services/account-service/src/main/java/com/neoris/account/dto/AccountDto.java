package com.neoris.account.dto;

import com.neoris.account.helper.AccountType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
@Valid
public record AccountDto (
        @NotNull
        Integer customerId,
        @NotBlank
        String accountNumber,
        @NotNull
        AccountType type,
        @NotNull
        BigDecimal balance
) {}
