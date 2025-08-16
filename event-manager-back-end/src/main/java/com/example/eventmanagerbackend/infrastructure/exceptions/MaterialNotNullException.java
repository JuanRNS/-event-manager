package com.example.eventmanagerbackend.infrastructure.exceptions;

public class MaterialNotNullException extends RuntimeException {
  public MaterialNotNullException(String message) {
    super(message);
  }
}
