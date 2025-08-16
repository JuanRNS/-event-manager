package com.example.eventmanagerbackend.infrastructure.exceptions;

public class UserNameStringFirstException extends RuntimeException {
    public UserNameStringFirstException(String message) {
        super(message);
    }

    public UserNameStringFirstException() {

      super("O username não pode ser nulo e deve iniciar com uma letra");
    }
}
