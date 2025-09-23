package com.example.eventmanagerbackend.infrastructure.mappers;

import com.example.eventmanagerbackend.domain.dtos.FestaGarcomResponseDTO;
import com.example.eventmanagerbackend.domain.dtos.FestaResponseDTO;
import com.example.eventmanagerbackend.domain.dtos.MaterialResponseDTO;
import com.example.eventmanagerbackend.domain.entities.Festa;
import com.example.eventmanagerbackend.domain.entities.FestaGarcom;
import com.example.eventmanagerbackend.domain.entities.Material;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FestaMapper {

    FestaResponseDTO toFestaResponseDTO(Festa festa);

    MaterialResponseDTO toMaterialResponseDTO(Material material);
}
