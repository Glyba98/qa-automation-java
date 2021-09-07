package com.tinkoff.edu.app.exceptions;

public class StorageIsFullException extends Exception {
    public StorageIsFullException(final String message) {
        super(message);
    }
}