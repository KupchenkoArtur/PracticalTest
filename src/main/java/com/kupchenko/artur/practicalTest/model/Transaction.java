package com.kupchenko.artur.practicalTest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "transactions")
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String accountNumberFrom;

    private String accountNumberTo;

    @Temporal(TemporalType.TIME)
    private LocalTime time;

    @Column(columnDefinition = "numeric")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private Operation operation;

    public Transaction(String accountNumberFrom, String accountNumberTo, LocalTime time, BigDecimal amount, Operation operation) {
        this.accountNumberFrom = accountNumberFrom;
        this.accountNumberTo = accountNumberTo;
        this.time = time;
        this.amount = amount;
        this.operation = operation;
    }
}
