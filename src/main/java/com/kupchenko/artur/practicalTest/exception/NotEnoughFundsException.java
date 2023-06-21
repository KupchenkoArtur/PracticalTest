package com.kupchenko.artur.practicalTest.exception;

public class NotEnoughFundsException extends RuntimeException {
    public NotEnoughFundsException() {
        super("Недостаточно средств на счете.");
    }
}