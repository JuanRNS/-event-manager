package com.example.eventmanagerbackend.infrastructure.controllers;

import com.example.eventmanagerbackend.infrastructure.exceptions.*;
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

    @ExceptionHandler(EmployeeNotFoundException.class)
    private ResponseEntity<String> garcomNotFoundException(EmployeeNotFoundException garcom) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(garcom.getMessage());
    }

    @ExceptionHandler(PartyNotFoundException.class)
    private ResponseEntity<String> festaNotFoundException(PartyNotFoundException festa) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(festa.getMessage());
    }

    @ExceptionHandler(UserNameStringFirstException.class)
    private ResponseEntity<String> userNameStringFirstException(UserNameStringFirstException user) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(user.getMessage());
    }
}
