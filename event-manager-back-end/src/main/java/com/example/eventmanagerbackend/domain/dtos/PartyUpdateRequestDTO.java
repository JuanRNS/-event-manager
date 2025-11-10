package com.example.eventmanagerbackend.domain.dtos;

import com.example.eventmanagerbackend.domain.enums.StatusParty;

import java.time.LocalDateTime;
import java.util.List;

public record PartyUpdateRequestDTO(
        String nameClient,
        String location,
        LocalDateTime date,

        List<EmployeePartiesValuesResponseDTO> values,
        Long idMaterial,
        Long numberOfPeople,
        StatusParty status
) {
}
