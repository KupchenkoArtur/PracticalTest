package com.kupchenko.artur.practicalTest.service;

import com.kupchenko.artur.practicalTest.model.Account;
import com.kupchenko.artur.practicalTest.repository.AccountRepository;
import com.kupchenko.artur.practicalTest.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountServiceImpl accountService;

//    @Test
//    public void createAccount() {
//        String name = "Test User";
//        String pin = "1234";
//        var account = new Account(name, pin);
//
//        when(accountRepository.save(account)).thenReturn(account);
//
//        Optional<Account> createdAccount = accountService.createAccount(name, pin);
//
//        assertEquals(createdAccount.orElseThrow(), account);
//
//
//
//    }


}