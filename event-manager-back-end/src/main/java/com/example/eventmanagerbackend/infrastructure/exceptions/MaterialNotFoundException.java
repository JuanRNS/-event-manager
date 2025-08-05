package com.example.eventmanagerbackend.infrastructure.exceptions;

public class MaterialNotFoundException extends RuntimeException {

    public MaterialNotFoundException() {
        super("Material não encontrado");
    }
    public MaterialNotFoundException(String message) {
        super(message);
    }
}
