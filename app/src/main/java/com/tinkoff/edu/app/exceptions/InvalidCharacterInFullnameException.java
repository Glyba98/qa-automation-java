package com.tinkoff.edu.app.exceptions;

public class InvalidCharacterInFullnameException extends Exception {
    public InvalidCharacterInFullnameException(final String message) {
        super(message);
    }
}
