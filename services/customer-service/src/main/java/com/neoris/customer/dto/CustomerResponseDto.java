package com.neoris.customer.dto;

import lombok.Builder;

@Builder
public record CustomerResponseDto(
        Integer id,
        String name,
        String gender,
        Integer age,
        String identification,
        String address,
        String phone,
        Boolean active
) {}
