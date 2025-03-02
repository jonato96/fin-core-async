package com.neoris.account.dto;

import com.neoris.account.helper.MovementType;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record MovementResponseDto(
        Integer id,
        LocalDate date,
        MovementType type,
        BigDecimal beforeBalance,
        BigDecimal amount,
        BigDecimal afterBalance
) {}
