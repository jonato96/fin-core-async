package com.neoris.account.mapper;

import com.neoris.account.domain.Account;
import com.neoris.account.dto.AccountDto;
import com.neoris.account.dto.AccountResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {
    @Mapping(target = "clientName", source = "clientName")
    AccountResponseDto toResponseDto(Account account, String clientName);
    List<AccountResponseDto> toResponseDtoList(List<Account> accounts);
    Account toAccount(AccountDto accountDto);
}
