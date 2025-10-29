package com.example.eventmanagerbackend.domain.dtos;

import com.example.eventmanagerbackend.domain.entities.EmployeePartiesValues;
import com.example.eventmanagerbackend.domain.entities.Material;
import com.example.eventmanagerbackend.domain.enums.StatusFesta;

import java.time.LocalDateTime;
import java.util.List;

public record PartyEmployeeViewDTO(
        Long id,
        String location,
        String nameClient,
        LocalDateTime date,
        List<EmployeePartiesValues> values,
        Material material,
        Long numberOfPeople,
        List<EmployeeResponseDTO> garcoms,
        StatusFesta status
) {
}
