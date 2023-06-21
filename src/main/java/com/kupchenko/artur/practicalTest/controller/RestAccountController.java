package com.kupchenko.artur.practicalTest.controller;

import com.kupchenko.artur.practicalTest.dto.AccountDto;
import com.kupchenko.artur.practicalTest.dto.PaymentDto;
import com.kupchenko.artur.practicalTest.exception.InvalidPinException;
import com.kupchenko.artur.practicalTest.exception.NotEnoughFundsException;
import com.kupchenko.artur.practicalTest.exception.NotFoundException;
import com.kupchenko.artur.practicalTest.model.Account;
import com.kupchenko.artur.practicalTest.service.AccountService;
import com.kupchenko.artur.practicalTest.service.AccountServiceImpl;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class RestAccountController {

    private final AccountService accountService;

    public RestAccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @PostMapping()
    public ResponseEntity<String> saveAccount(@RequestBody AccountDto accountJson) {
        String name = accountJson.getName();
        String pin = accountJson.getPin();

        try {
            var account = accountService.createAccount(name, pin);
        } catch (InvalidPinException e) {
            return new ResponseEntity<>("Длина пин-кода не соответсвует", HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>("Новый аккаунт успешно создан", HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {

        var allAccounts = accountService.getAllAccounts();

        return new ResponseEntity<>(allAccounts, HttpStatus.OK);
    }

    @GetMapping(value = "/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable @NotBlank String accountNumber) {
        var accountByAccountNumber = accountService.getAccountByAccountNumber(accountNumber);

        return accountByAccountNumber.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(value = "/{accountNumber}/deposit")
    public ResponseEntity<String> deposit(@PathVariable @NotBlank String accountNumber, @RequestBody PaymentDto paymentDto) {

        var amount = paymentDto.getAmount();

        try {
            accountService.deposit(accountNumber, amount);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }


        return new ResponseEntity<>("Перевод выполнен успешно", HttpStatus.OK);
    }

    @PatchMapping(value = "/{fromAccountNumber}/transfer/{toAccountNumber}")
    public ResponseEntity<String> transfer(@PathVariable @NotBlank String fromAccountNumber,
                                           @PathVariable @NotBlank String toAccountNumber,
                                           @RequestBody PaymentDto paymentDto) {

        var amount = paymentDto.getAmount();
        var pin = paymentDto.getPin();

        try {
            accountService.transfer(fromAccountNumber, toAccountNumber, amount, pin);

        } catch (NotFoundException e) {
            return new ResponseEntity<>("Ошибка перевода", HttpStatus.BAD_REQUEST);
        } catch (InvalidPinException e) {
            return new ResponseEntity<>("Неверный пин", HttpStatus.BAD_REQUEST);
        } catch (NotEnoughFundsException e) {
            return new ResponseEntity<>("Недостаточно средств", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Перевод выполнен успешно", HttpStatus.OK);

    }

    @PatchMapping(value = "/{accountNumber}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable @NotBlank String accountNumber,
                                           @RequestBody PaymentDto paymentDto) {

        var amount = paymentDto.getAmount();
        var pin = paymentDto.getPin();

        try {
            accountService.withdraw(accountNumber, amount, pin);

        } catch (NotFoundException e) {
            return new ResponseEntity<>("Ошибка перевода", HttpStatus.BAD_REQUEST);
        } catch (NotEnoughFundsException e) {
            return new ResponseEntity<>("Недостаточно средств", HttpStatus.BAD_REQUEST);
        } catch (InvalidPinException e) {
            return new ResponseEntity<>("Неверный пин", HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>("Перевод выполнен успешно", HttpStatus.OK);


    }


}
