package com.example.eventmanagerbackend.domain.dtos;

import com.example.eventmanagerbackend.domain.entities.EmployeeType;
import com.example.eventmanagerbackend.domain.entities.Party;
import com.example.eventmanagerbackend.domain.enums.StatusGarcom;

import java.util.List;


public record EmployeeResponseDTO(
    Long id,
    String name,
    String pixKey,
    String phone,
    StatusGarcom statusGarcom,
    EmployeeType employeeType,
    List<Party> parties
) {
}
