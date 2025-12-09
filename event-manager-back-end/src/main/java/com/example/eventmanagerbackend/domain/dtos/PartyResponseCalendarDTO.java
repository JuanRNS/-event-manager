package com.example.eventmanagerbackend.domain.dtos;

import java.time.LocalDateTime;

public record PartyResponseCalendarDTO(
        Long id,
        String nameClient,
        String location,
        LocalDateTime date
) {
}
