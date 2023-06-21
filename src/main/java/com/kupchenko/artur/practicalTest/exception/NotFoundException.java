package com.kupchenko.artur.practicalTest.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String string) {
        super(string);
    }
}
