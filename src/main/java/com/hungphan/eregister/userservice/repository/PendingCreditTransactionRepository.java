package com.hungphan.eregister.userservice.repository;

import com.hungphan.eregister.userservice.model.PendingCreditTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PendingCreditTransactionRepository extends JpaRepository<PendingCreditTransaction, Long> {

    PendingCreditTransaction findByRequestId(String requestId);

}
