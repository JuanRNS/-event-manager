package com.example.eventmanagerbackend.infrastructure.controllers;

import com.example.eventmanagerbackend.infrastructure.exceptions.EmailExistsException;
import com.example.eventmanagerbackend.infrastructure.exceptions.FestaNotFoundException;
import com.example.eventmanagerbackend.infrastructure.exceptions.GarcomNotFoundException;
import com.example.eventmanagerbackend.infrastructure.exceptions.MaterialNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EmailExistsException.class)
    private ResponseEntity<String> emailExistsException(EmailExistsException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }
    @ExceptionHandler(MaterialNotFoundException.class)
    private ResponseEntity<String> materialNotFoundException(MaterialNotFoundException material) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(material.getMessage());
    }

    @ExceptionHandler(GarcomNotFoundException.class)
    private ResponseEntity<String> garcomNotFoundException(GarcomNotFoundException garcom) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(garcom.getMessage());
    }

    @ExceptionHandler(FestaNotFoundException.class)
    private ResponseEntity<String> festaNotFoundException(FestaNotFoundException festa) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(festa.getMessage());
    }
}
