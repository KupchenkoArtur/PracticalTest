package com.kupchenko.artur.practicalTest.controller;

import com.kupchenko.artur.practicalTest.model.Transaction;
import com.kupchenko.artur.practicalTest.service.TransactionService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class RestTransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        var allTransactions = transactionService.getAllTransactions();

        return new ResponseEntity<>(allTransactions, HttpStatus.OK);
    }

    @GetMapping(value = "/{accountNumber}")
    public ResponseEntity<List<Transaction>> getAllTransactionsById(@PathVariable @NotBlank String accountNumber) {

        var allTransactionsByAccountNumber = transactionService.getAllTransactionsByAccountNumber(accountNumber);

        return new ResponseEntity<>(allTransactionsByAccountNumber, HttpStatus.OK);
    }
}
