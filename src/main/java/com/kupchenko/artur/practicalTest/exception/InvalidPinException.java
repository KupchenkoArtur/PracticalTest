package com.kupchenko.artur.practicalTest.exception;

public class InvalidPinException extends RuntimeException{
    public InvalidPinException(String message) {
        super(message);
    }
}
