package com.example.eventmanagerbackend.domain.dtos;

import java.util.List;

public record EmployeePartyDTO(
        String name,
        String phone,
        String pixKey,
        List<PartyByEmployeeIdDTO> parties
) {
}
