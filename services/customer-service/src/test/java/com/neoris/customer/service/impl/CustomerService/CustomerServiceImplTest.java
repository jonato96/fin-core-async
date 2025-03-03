package com.neoris.customer.service.impl.CustomerService;

import com.neoris.commons.library.exception.FinCoreException;
import com.neoris.customer.domain.Customer;
import com.neoris.customer.dto.CustomerResponseDto;
import com.neoris.customer.mapper.CustomerMapper;
import com.neoris.customer.repository.CustomerRepository;
import com.neoris.customer.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Test
    void testFindById() {
        // Mock data
        int id = 1;
        Customer customerFind = new Customer();
        customerFind.setId(1);
        when(customerRepository.findById(id)).thenReturn(Optional.of(customerFind));

        CustomerResponseDto mapped = CustomerResponseDto.builder().id(1).build();
        when(customerMapper.toDto(customerFind))
                .thenReturn(mapped);
        // Act
        CustomerResponseDto result = customerServiceImpl.findById(id);
        // Assert
        assertNotNull(result);
        assertEquals(1, result.id());
    }

    @Test
    void testFindById_ExceptionCase() {
        // Mock Data
        int id = 1;
        when(customerRepository.findById(id)).thenReturn(Optional.empty());
        // Assert
        assertThrows(FinCoreException.class, () -> customerServiceImpl.findById(id));
    }

}
