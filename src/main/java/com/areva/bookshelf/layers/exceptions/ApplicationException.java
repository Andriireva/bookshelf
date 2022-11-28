package com.areva.bookshelf.layers.exceptions;

// All your custom exception MUST be extended from RuntimeException (based on best practice)
public class ApplicationException extends RuntimeException {
    public ApplicationException() {
    }

    public ApplicationException(String message) {
        super(message);
    }
}
