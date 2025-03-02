package com.neoris.account.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ReportResponseDto(
        String client,
        String accountNumber,
        String accountType,
        BigDecimal balance,
        List<ReportMovementDto> transactionDtoList
) {}
