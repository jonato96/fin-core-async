package com.neoris.customer.rabbit;

import com.neoris.customer.config.RabbitConfig;
import com.neoris.customer.dto.CustomerResponseDto;
import com.neoris.customer.mapper.CustomerMapper;
import com.neoris.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerCostumer {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @RabbitListener(queues = RabbitConfig.QUEUE_CLIENT)
    public CustomerResponseDto findCustomer(Integer customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::toDto)
                .orElse(null);
    }

}
