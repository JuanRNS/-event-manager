package com.example.eventmanagerbackend.domain.dtos;

import java.util.List;

public record PartyEmployeeAddRequestDTO(
        List<Long> employeeIds,
        Long partyId
) {
}
