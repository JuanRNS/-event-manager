package com.example.eventmanagerbackend.domain.dtos;

import java.util.List;

public record FestaGarcomRequestDTO (
        Long festaId,
        List<Long> garcomIds
){
}
