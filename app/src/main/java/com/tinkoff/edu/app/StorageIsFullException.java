package com.tinkoff.edu.app;

public class StorageIsFullException extends Exception {
    public StorageIsFullException(final String message) {
        super(message);
    }
}