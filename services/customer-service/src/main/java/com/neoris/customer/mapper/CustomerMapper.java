package com.neoris.customer.mapper;

import com.neoris.customer.domain.Customer;
import com.neoris.customer.dto.CustomerDto;
import com.neoris.customer.dto.CustomerResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    Customer toCustomer(CustomerDto request);
    CustomerResponseDto toDto(Customer customer);
    List<CustomerResponseDto> toListDto(List<Customer> customers);
}
