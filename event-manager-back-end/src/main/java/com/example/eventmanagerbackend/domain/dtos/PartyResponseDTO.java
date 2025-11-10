package com.example.eventmanagerbackend.domain.dtos;


import com.example.eventmanagerbackend.domain.enums.StatusParty;

import java.time.LocalDateTime;
import java.util.List;

public record PartyResponseDTO(
    Long id,
    String location,
    String nameClient,
    LocalDateTime date,
    List<EmployeePartiesValuesResponseDTO> values,
    MaterialResponseDTO material,
    List<Long> employeeId,
    Long numberOfPeople,
    StatusParty status
) {
}
