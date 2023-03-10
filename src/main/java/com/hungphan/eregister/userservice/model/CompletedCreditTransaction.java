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
public class CompletedCreditTransaction {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "COMPLETED_CREDIT_TRANSACTION_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "COMPLETED_CREDIT_TRANSACTION_GEN", sequenceName = "COMPLETED_CREDIT_TRANSACTION_SEQ", allocationSize = 1)
    private Long id;

    private Long creditAmount;
    private String username;
    private String description;
    private boolean isCancelled;

}
