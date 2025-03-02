package com.neoris.account.service;

import com.neoris.account.domain.Movement;
import com.neoris.account.dto.MovementDto;
import com.neoris.account.dto.MovementResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface MovementService {

    MovementResponseDto save(MovementDto request);
    MovementResponseDto findById(Integer id);
    List<MovementResponseDto> findByAccount(String account);
    List<Movement> findByAccountAndDates(Integer accountId, LocalDate start, LocalDate end);

}
