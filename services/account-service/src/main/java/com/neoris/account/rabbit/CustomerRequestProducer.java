package com.neoris.account.rabbit;


import com.neoris.account.config.RabbitConfig;
import com.neoris.account.dto.client.CustomerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerRequestProducer {

    private final RabbitTemplate rabbitTemplate;

    public CustomerResponseDto findCustomer(Integer clientId) {
        ParameterizedTypeReference<CustomerResponseDto> typeReference = new ParameterizedTypeReference<>(){};
        return rabbitTemplate.convertSendAndReceiveAsType(
                RabbitConfig.QUEUE_CLIENT,
                clientId,
                typeReference
        );
    }

}
