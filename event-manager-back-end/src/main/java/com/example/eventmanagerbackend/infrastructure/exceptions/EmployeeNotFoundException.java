package com.example.eventmanagerbackend.infrastructure.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }

    public EmployeeNotFoundException() {
        super("Funcionário não encontrado");
    }
}
