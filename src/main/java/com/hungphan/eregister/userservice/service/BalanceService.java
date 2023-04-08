package com.hungphan.eregister.userservice.service;

import com.hungphan.eregister.userservice.dto.HoldingCreditRequestDto;
import com.hungphan.eregister.userservice.dto.HoldingCreditResponseDto;
import com.hungphan.eregister.userservice.model.CompletedCreditTransaction;
import com.hungphan.eregister.userservice.model.PendingCreditTransaction;
import com.hungphan.eregister.userservice.model.User;
import com.hungphan.eregister.userservice.repository.CompletedCreditTransactionRepository;
import com.hungphan.eregister.userservice.repository.PendingCreditTransactionRepository;
import com.hungphan.eregister.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BalanceService {

    @Autowired
    private PendingCreditTransactionRepository pendingCreditTransactionRepository;

    @Autowired
    private CompletedCreditTransactionRepository completedCreditTransactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public HoldingCreditResponseDto holdCredit(HoldingCreditRequestDto holdingCreditRequestDto) {
        PendingCreditTransaction pendingCreditTransaction = new PendingCreditTransaction();
        pendingCreditTransaction.setCreditAmount(holdingCreditRequestDto.getCreditAmount());
        pendingCreditTransaction.setUsername(holdingCreditRequestDto.getUsername());
        pendingCreditTransaction.setDescription(holdingCreditRequestDto.getDescription());
        pendingCreditTransaction.setRequestId(holdingCreditRequestDto.getRequestId());
        PendingCreditTransaction persistedPendingCreditTransaction = pendingCreditTransactionRepository.save(pendingCreditTransaction);

        User user = userRepository.findByUsername(holdingCreditRequestDto.getUsername());
        user.setUnavailableBalance(user.getUnavailableBalance() + holdingCreditRequestDto.getCreditAmount());
        userRepository.save(user);

        return new HoldingCreditResponseDto(persistedPendingCreditTransaction.getId());
    }

    @Transactional
    public void useCredit(String requestId) {
        PendingCreditTransaction pendingCreditTransaction = pendingCreditTransactionRepository.findByRequestId(requestId);
        if (pendingCreditTransaction == null) return;
        pendingCreditTransactionRepository.delete(pendingCreditTransaction);
        CompletedCreditTransaction completedCreditTransaction = new CompletedCreditTransaction();
        completedCreditTransaction.setCreditAmount(pendingCreditTransaction.getCreditAmount());
        completedCreditTransaction.setUsername(pendingCreditTransaction.getUsername());
        completedCreditTransaction.setDescription(pendingCreditTransaction.getDescription());
        completedCreditTransaction.setCancelled(false);
        completedCreditTransactionRepository.save(completedCreditTransaction);

        User user = userRepository.findByUsername(pendingCreditTransaction.getUsername());
        user.setUnavailableBalance(user.getUnavailableBalance() - pendingCreditTransaction.getCreditAmount());
        user.setBalance(user.getBalance() - pendingCreditTransaction.getCreditAmount());
    }

    @Transactional
    public void releaseCredit(String requestId) {
        PendingCreditTransaction pendingCreditTransaction = pendingCreditTransactionRepository.findByRequestId(requestId);
        if (pendingCreditTransaction == null) return;
        pendingCreditTransactionRepository.delete(pendingCreditTransaction);
        CompletedCreditTransaction completedCreditTransaction = new CompletedCreditTransaction();
        completedCreditTransaction.setCreditAmount(pendingCreditTransaction.getCreditAmount());
        completedCreditTransaction.setUsername(pendingCreditTransaction.getUsername());
        completedCreditTransaction.setDescription(pendingCreditTransaction.getDescription());
        completedCreditTransaction.setCancelled(true);
        completedCreditTransactionRepository.save(completedCreditTransaction);

        User user = userRepository.findByUsername(pendingCreditTransaction.getUsername());
        user.setUnavailableBalance(user.getUnavailableBalance() - pendingCreditTransaction.getCreditAmount());
    }

}
