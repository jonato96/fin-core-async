package com.neoris.account.controller;

import com.neoris.account.dto.AccountDto;
import com.neoris.account.dto.AccountResponseDto;
import com.neoris.account.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.neoris.commons.library.constant.GeneralConstant.ID_IN_PATH;

@RestController
@RequestMapping("api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountResponseDto>> findAll() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping(ID_IN_PATH)
    public ResponseEntity<AccountResponseDto> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(accountService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AccountResponseDto> create(@RequestBody @Valid AccountDto accountDto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.save(accountDto));
    }

    @DeleteMapping(ID_IN_PATH)
    public ResponseEntity<String> deactivate(@PathVariable("id") Integer id) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
