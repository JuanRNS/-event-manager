package com.example.eventmanagerbackend.infrastructure.mappers;

import com.example.eventmanagerbackend.domain.dtos.FestaGarcomResponseDTO;
import com.example.eventmanagerbackend.domain.dtos.FestaRequestDTO;
import com.example.eventmanagerbackend.domain.dtos.FestaResponseDTO;
import com.example.eventmanagerbackend.domain.dtos.MaterialResponseDTO;
import com.example.eventmanagerbackend.domain.entities.Festa;
import com.example.eventmanagerbackend.domain.entities.FestaGarcom;
import com.example.eventmanagerbackend.domain.entities.Material;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FestaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "festaGarcoms", ignore = true)
    Festa toFesta(FestaRequestDTO festaRequestDTO);
    FestaResponseDTO toFestaResponseDTO(Festa festa);

    MaterialResponseDTO toMaterialResponseDTO(Material material);
    FestaGarcomResponseDTO toFestaGarcomResponseDTO(FestaGarcom festaGarcom);


    List<FestaGarcomResponseDTO> toFestaGarcomResponseDTO(List<FestaGarcom> festaGarcoms);
}
