package com.neoris.customer.controller;

import com.neoris.customer.dto.CustomerDto;
import com.neoris.customer.service.CustomerService;
import com.neoris.customer.dto.CustomerResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.neoris.commons.library.constant.GeneralConstant.ID_IN_PATH;

@RestController
@RequestMapping("api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping(ID_IN_PATH)
    public ResponseEntity<CustomerResponseDto> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> create(@RequestBody @Valid CustomerDto customerDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customerDto));
    }

    @PutMapping(ID_IN_PATH)
    public ResponseEntity<CustomerResponseDto> edit(@PathVariable Integer id, @RequestBody @Valid CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.edit(id, customerDto));
    }

    @DeleteMapping(ID_IN_PATH)
    public ResponseEntity<String> deactivate(@PathVariable("id") Integer id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
