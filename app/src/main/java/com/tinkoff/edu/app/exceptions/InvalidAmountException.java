package com.tinkoff.edu.app.exceptions;

public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException(final String message) {
        super(message);
    }

}
