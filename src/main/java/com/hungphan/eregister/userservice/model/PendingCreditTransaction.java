package com.hungphan.eregister.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PendingCreditTransaction {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "PENDING_CREDIT_TRANSACTION_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "PENDING_CREDIT_TRANSACTION_GEN", sequenceName = "PENDING_CREDIT_TRANSACTION_SEQ", allocationSize = 1)
    private Long id;
    private String requestId;
    private Long creditAmount;
    private String username;
    private String description;

}
