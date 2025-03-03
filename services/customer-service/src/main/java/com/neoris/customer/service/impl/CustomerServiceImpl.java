package com.neoris.customer.service.impl;

import com.neoris.commons.library.exception.FinCoreException;
import com.neoris.customer.domain.Customer;
import com.neoris.customer.dto.CustomerDto;
import com.neoris.customer.dto.CustomerResponseDto;
import com.neoris.customer.mapper.CustomerMapper;
import com.neoris.customer.repository.CustomerRepository;
import com.neoris.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public CustomerResponseDto save(CustomerDto requestCustomer) {
        Customer customerToCreate = customerMapper.toCustomer(requestCustomer);
        customerToCreate.setPassword(passwordEncoder.encode(customerToCreate.getPassword()));
        customerToCreate.setActive(Boolean.TRUE);
        Customer customerCreated = customerRepository.save(customerToCreate);
        return customerMapper.toDto(customerCreated);
    }

    @Override
    @Transactional
    public CustomerResponseDto edit(Integer id, CustomerDto requestClient) {
        Customer customerFind = customerRepository.findByIdAndActiveTrue(id)
                .orElseThrow( () -> new FinCoreException("Client not found with id: " + id + ", or is already inactive"));
        Customer customerToEdit = customerMapper.toCustomer(requestClient);
        customerToEdit.setId(customerFind.getId());
        customerToEdit.setActive(customerFind.isActive());
        customerToEdit.setPassword(passwordEncoder.encode(customerToEdit.getPassword()));
        Customer clientCreated = customerRepository.save(customerToEdit);
        return customerMapper.toDto(clientCreated);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        validateExistsAndIsActive(id);
        customerRepository.inactivateClient(id);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponseDto findById(Integer id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDto)
                .orElseThrow( () -> new FinCoreException("Customer not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponseDto> findAll() {
        return customerMapper.toListDto(customerRepository.findAll());
    }

    private void validateExistsAndIsActive(Integer id) {
        boolean result = customerRepository.existsByIdAndActiveTrue(id);
        if (!result) throw new FinCoreException("Customer not found with id: " + id + ", or is already inactive");
    }
}
