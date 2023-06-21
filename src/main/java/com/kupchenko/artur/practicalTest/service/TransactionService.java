package com.kupchenko.artur.practicalTest.service;

import com.kupchenko.artur.practicalTest.model.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAllTransactions();
    List<Transaction> getAllTransactionsByAccountNumber(String accountNumber);
}
