package com.example.eventmanagerbackend.infrastructure.exceptions;

public class UserNameExistsException extends RuntimeException {
  public UserNameExistsException(String message) {
    super(message);
  }
}
