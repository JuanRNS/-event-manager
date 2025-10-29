package com.example.eventmanagerbackend.infrastructure.exceptions;

public class UserNameExistsException extends RuntimeException {
    public UserNameExistsException(String message) {
        super(message);
    }

    public UserNameExistsException(){
        super("Usuário já cadastrado");
    }
}
