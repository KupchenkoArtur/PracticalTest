package com.kupchenko.artur.practicalTest.service;

import com.kupchenko.artur.practicalTest.model.Transaction;
import com.kupchenko.artur.practicalTest.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getAllTransactionsByAccountNumber(String accountNumber) {
        return transactionRepository.findTransactionsByAccountNumberFrom(accountNumber);
    }
}
