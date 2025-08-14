package com.example.eventmanagerbackend.infrastructure.mappers;

import com.example.eventmanagerbackend.domain.dtos.MaterialResponseDTO;
import com.example.eventmanagerbackend.domain.entities.Material;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaterialMapper {

    MaterialResponseDTO toMaterialResponseDTO(Material material);
}
