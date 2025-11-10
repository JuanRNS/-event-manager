package com.example.eventmanagerbackend.domain.dtos;

import com.example.eventmanagerbackend.domain.entities.EmployeeType;
import com.example.eventmanagerbackend.domain.enums.StatusEmployee;

public record EmployeeAllResponseDTO (
        Long id,
        String name,
        String pixKey,
        String phone,
        StatusEmployee statusEmployee,
        EmployeeType employeeType
){
}
