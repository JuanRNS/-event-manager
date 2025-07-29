package com.example.eventmanagerbackend.infrastructure.mappers;

import com.example.eventmanagerbackend.domain.dtos.FestaRequestDTO;
import com.example.eventmanagerbackend.domain.dtos.FestaResponseDTO;
import com.example.eventmanagerbackend.domain.entities.Festa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FestaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "FestaGarcoms", ignore = true)
    Festa toFesta(FestaRequestDTO festaRequestDTO);
    FestaResponseDTO toFestaResponseDTO(Festa festa);
}
