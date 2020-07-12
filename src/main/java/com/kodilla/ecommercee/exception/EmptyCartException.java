package com.kodilla.ecommercee.exception;

public class EmptyCartException extends RuntimeException {
    public EmptyCartException() {
        super("Empty cart");
    }
}
