package com.kupchenko.artur.practicalTest.service;

import com.kupchenko.artur.practicalTest.model.Operation;
import com.kupchenko.artur.practicalTest.model.Transaction;
import com.kupchenko.artur.practicalTest.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    public void testGetAllTransactions() {
        var transaction1 = new Transaction(1L,"12345","12345",
                LocalTime.now(),BigDecimal.TEN,Operation.DEPOSIT);

        var transaction2 = new Transaction(2L,"54525","352553",
                LocalTime.now(),BigDecimal.TEN,Operation.TRANSFER);

        var transactions = Arrays.asList(transaction1, transaction2);

        when(transactionRepository.findAll()).thenReturn(transactions);

        var result = transactionService.getAllTransactions();

        assertEquals(transactions, result);
    }

    @Test
    public void testGetAllTransactionsByAccountNumber() {
        var transaction1 = new Transaction(1L,"12345","12345",
                LocalTime.now(),BigDecimal.TEN,Operation.DEPOSIT);

        var transaction2 = new Transaction(2L,"54525","352553",
                LocalTime.now(),BigDecimal.TEN,Operation.TRANSFER);

        var transactions = Arrays.asList(transaction1, transaction2);

        when(transactionRepository.findTransactionsByAccountNumberFrom("12345")).thenReturn(Arrays.asList(transaction1));

        var result = transactionService.getAllTransactionsByAccountNumber("12345");

        assertEquals(Arrays.asList(transaction1), result);
    }
}