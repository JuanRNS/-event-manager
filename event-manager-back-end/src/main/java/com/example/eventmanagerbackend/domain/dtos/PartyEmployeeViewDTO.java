package com.example.eventmanagerbackend.domain.dtos;

import com.example.eventmanagerbackend.domain.entities.EmployeePartiesValues;
import com.example.eventmanagerbackend.domain.enums.StatusParty;

import java.time.LocalDateTime;
import java.util.List;

public record PartyEmployeeViewDTO(
        Long id,
        String location,
        String nameClient,
        LocalDateTime date,
        List<EmployeePartiesValues> values,
        MaterialResponseDTO material,
        Long numberOfPeople,
        List<EmployeeIdResponseDTO> employees,
        StatusParty status
) {
}
