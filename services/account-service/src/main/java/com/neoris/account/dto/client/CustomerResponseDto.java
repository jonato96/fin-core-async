package com.neoris.account.dto.client;

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
