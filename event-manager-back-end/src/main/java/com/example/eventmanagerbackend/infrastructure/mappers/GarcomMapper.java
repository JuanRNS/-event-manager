package com.example.eventmanagerbackend.infrastructure.mappers;

import com.example.eventmanagerbackend.domain.dtos.GarcomOptionsResponseDTO;
import com.example.eventmanagerbackend.domain.dtos.GarcomRequestDTO;
import com.example.eventmanagerbackend.domain.dtos.GarcomResponseDTO;
import com.example.eventmanagerbackend.domain.entities.Garcom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GarcomMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "festaGarcoms", ignore = true)
    Garcom toGarcom(GarcomRequestDTO garcomRequestDTO);
    GarcomRequestDTO toGarcomDTO(Garcom garcom);
    @Mapping(target = "description", source = "name")
    GarcomOptionsResponseDTO toGarcomOptionsResponseDTO(Garcom garcom);

    GarcomResponseDTO toGarcomResponseDTO(Garcom garcom);
}
