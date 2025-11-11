package com.example.eventmanagerbackend.infrastructure.mappers;

import com.example.eventmanagerbackend.domain.dtos.PartyResponseDTO;
import com.example.eventmanagerbackend.domain.dtos.MaterialResponseDTO;
import com.example.eventmanagerbackend.domain.entities.Party;
import com.example.eventmanagerbackend.domain.entities.Material;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PartyMapper {

    PartyResponseDTO toPartyResponseDTO(Party party);

    MaterialResponseDTO toMaterialResponseDTO(Material material);
}
