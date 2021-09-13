package com.tinkoff.edu.app.exceptions;

public class InvalidCharacterInFullnameException extends RuntimeException {
    public InvalidCharacterInFullnameException(final String message) {
        super(message);
    }
}
