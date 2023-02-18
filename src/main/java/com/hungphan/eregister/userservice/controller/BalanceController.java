package com.hungphan.eregister.userservice.controller;

import com.hungphan.eregister.userservice.dto.HoldingCreditRequestDto;
import com.hungphan.eregister.userservice.dto.HoldingCreditResponseDto;
import com.hungphan.eregister.userservice.service.BalanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BalanceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BalanceController.class);

    @Autowired
    private BalanceService balanceService;

    @PostMapping("/balance/hold-credit")
    ResponseEntity<HoldingCreditResponseDto> holdCredit(@RequestBody HoldingCreditRequestDto holdingCreditRequestDto) {
        try {
            HoldingCreditResponseDto holdingCreditResponseDto = balanceService.holdCredit(holdingCreditRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(holdingCreditResponseDto);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/balance/use-credit/{transactionId}")
    ResponseEntity<Void> useCredit(@PathVariable Long transactionId) {
        try {
            balanceService.useCredit(transactionId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/balance/release-credit/{transactionId}")
    ResponseEntity<Void> releaseCredit(@PathVariable Long transactionId) {
        try {
            balanceService.releaseCredit(transactionId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
