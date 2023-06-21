package com.kupchenko.artur.practicalTest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "accounts")
@AllArgsConstructor
public class Account {

    @Id
    private String accountNumber;

    private String name;

    @Column(length = 4)
    private String pin;

    @Column(columnDefinition = "numeric")
    private BigDecimal amount;

    public Account(String name, String pin) {
        this.accountNumber = UUID.randomUUID().toString();
        this.name = name;
        this.pin = pin;
        this.amount = BigDecimal.valueOf(0);
    }

}
