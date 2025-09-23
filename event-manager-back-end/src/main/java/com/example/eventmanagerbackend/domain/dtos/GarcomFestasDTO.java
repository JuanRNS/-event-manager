package com.example.eventmanagerbackend.domain.dtos;

import java.util.List;

public record GarcomFestasDTO(
        String name,
        String phone,
        String pixKey,
        List<FestasByGarcomIdDTO> parties
) {
}
