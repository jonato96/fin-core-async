package com.neoris.customer.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Valid
public record CustomerDto(
        @NotBlank(message = "Customer name is required")
        String name,
        @NotBlank(message = "Customer gender is required")
        String gender,
        @NotNull(message = "Customer age is required")
        @Min(18)
        @Max(110)
        Integer age,
        @NotBlank(message = "Customer identification is required")
        @Size(min = 1, max = 13, message = "Identification must be between 10 and 13 characters")
        String identification,
        @NotBlank(message = "Customer address is required")
        String address,
        @NotBlank(message = "Customer phone is required")
        String phone,
        @NotBlank(message = "Customer password is required")
        String password
) {}
