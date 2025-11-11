package com.example.eventmanagerbackend.domain.dtos;


public record EmployeeAddResponseDTO(
    Long id,
    String name,
    String phone
) {
}
