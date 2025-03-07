package com.neoris.customer.service;

import com.neoris.customer.dto.CustomerDto;
import com.neoris.customer.dto.CustomerResponseDto;

import java.util.List;

public interface CustomerService {

    CustomerResponseDto save(CustomerDto requestCustomer);
    CustomerResponseDto edit(Integer id, CustomerDto requestCustomer);
    void delete(Integer id);
    CustomerResponseDto findById(Integer id);
    List<CustomerResponseDto> findAll();

}
