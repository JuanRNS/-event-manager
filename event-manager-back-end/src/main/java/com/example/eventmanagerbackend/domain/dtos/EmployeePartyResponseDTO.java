package com.example.eventmanagerbackend.domain.dtos;

import com.example.eventmanagerbackend.domain.entities.EmployeeType;
import com.example.eventmanagerbackend.domain.entities.Party;
import com.example.eventmanagerbackend.domain.enums.StatusEmployee;

import java.util.List;


public record EmployeePartyResponseDTO(
    Long id,
    String name,
    String pixKey,
    String phone,
    StatusEmployee statusEmployee,
    EmployeeType employeeType,
    List<Party> parties
) {
}
