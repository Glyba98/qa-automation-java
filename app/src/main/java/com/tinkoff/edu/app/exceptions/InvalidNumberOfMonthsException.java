package com.tinkoff.edu.app.exceptions;

public class InvalidNumberOfMonthsException extends RuntimeException {
    public InvalidNumberOfMonthsException(final String message) {
        super(message);
    }
}
