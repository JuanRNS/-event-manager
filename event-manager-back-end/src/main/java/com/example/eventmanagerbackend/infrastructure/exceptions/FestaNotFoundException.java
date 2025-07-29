package com.example.eventmanagerbackend.infrastructure.exceptions;

public class FestaNotFoundException extends RuntimeException {
    public FestaNotFoundException(String message) {
        super(message);
    }
    public FestaNotFoundException() {
        super("Festa nao encontrada");
    }
}
