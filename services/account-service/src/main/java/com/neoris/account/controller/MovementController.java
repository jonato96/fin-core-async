package com.neoris.account.controller;

import com.neoris.account.dto.MovementDto;
import com.neoris.account.dto.MovementResponseDto;
import com.neoris.account.dto.ReportResponseDto;
import com.neoris.account.service.MovementService;
import com.neoris.account.util.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static com.neoris.commons.library.constant.GeneralConstant.ID_IN_PATH;

@RestController
@RequestMapping("api/movements")
@RequiredArgsConstructor
public class MovementController {

    private final MovementService movementService;
    private final ReportService reportService;

    @GetMapping(ID_IN_PATH)
    public ResponseEntity<MovementResponseDto> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(movementService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<MovementResponseDto>> findByAndAccount(
            @RequestParam("accountNumber") String accountNumber) {
        return ResponseEntity.ok(movementService.findByAccount(accountNumber));
    }

    @PostMapping
    public ResponseEntity<MovementResponseDto> create(@RequestBody MovementDto movementDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movementService.save(movementDto));
    }

    @GetMapping("report")
    public ResponseEntity<List<ReportResponseDto>> generateReport(
            @RequestParam("customerId") Integer customerId,
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate) {

        return ResponseEntity.ok(reportService.generateReport(customerId, startDate, endDate));
    }

}
