package com.neoris.account.dto;

import com.neoris.account.helper.MovementType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Valid
@Builder
public record MovementDto (
        @NotNull
        Integer accountId,
        @NotBlank
        MovementType type,
        @NotNull
        BigDecimal amount
){}
