package com.example.eventmanagerbackend.infrastructure.exceptions;

public class GarcomNotFoundException extends RuntimeException {
    public GarcomNotFoundException(String message) {
        super(message);
    }

    public GarcomNotFoundException() {
        super("Garcom não encontrado");
    }
}
