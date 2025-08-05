package com.example.eventmanagerbackend.infrastructure.exceptions;

public class EmailExistsException extends RuntimeException{

    public EmailExistsException() {
        super("Já existe um usuario com esse email");
    }

    public EmailExistsException(String message) {
        super(message);
    }
}
