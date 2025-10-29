package com.example.eventmanagerbackend.infrastructure.exceptions;

public class EmployeeTypeNotFoundException extends RuntimeException {
  public EmployeeTypeNotFoundException(String message) {
    super(message);
  }
}
