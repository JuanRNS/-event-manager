package com.example.eventmanagerbackend.infrastructure.exceptions;

public class PartyNotFoundException extends RuntimeException {
    public PartyNotFoundException(String message) {
        super(message);
    }
    public PartyNotFoundException() {
        super("Festa nao encontrada");
    }
}
