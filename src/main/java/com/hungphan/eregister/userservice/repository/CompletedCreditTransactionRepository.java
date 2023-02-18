package com.hungphan.eregister.userservice.repository;

import com.hungphan.eregister.userservice.model.CompletedCreditTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompletedCreditTransactionRepository extends JpaRepository<CompletedCreditTransaction, Long> {
}
