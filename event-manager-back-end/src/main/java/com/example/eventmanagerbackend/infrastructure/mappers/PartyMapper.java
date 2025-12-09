package com.example.eventmanagerbackend.infrastructure.mappers;

import com.example.eventmanagerbackend.domain.dtos.*;
import com.example.eventmanagerbackend.domain.entities.Employee;
import com.example.eventmanagerbackend.domain.entities.EmployeePartiesValues;
import com.example.eventmanagerbackend.domain.entities.Party;
import com.example.eventmanagerbackend.domain.entities.Material;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PartyMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "material", ignore = true)
    @Mapping(target = "partyEmployees", ignore = true)
    @Mapping(target = "values", ignore = true)
    @Mapping(target = "status", constant = "AGENDADA")
    Party toEntity(PartyRequestDTO dto);

    @Mapping(source = "partyEmployees", target = "employeeId", qualifiedByName = "mapEmployeesToIds")
    @Mapping(source = "values", target = "values")
    PartyResponseDTO toResponseDTO(Party party);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "material", ignore = true)
    @Mapping(target = "partyEmployees", ignore = true)
    @Mapping(target = "values", ignore = true)
    void updatePartyFromDTO(PartyUpdateRequestDTO dto, @MappingTarget Party party);

    MaterialResponseDTO toMaterialResponseDTO(Material material);

    @Mapping(source = "employeeType", target = "employeeType")
    EmployeePartiesValuesResponseDTO toValuesResponseDTO(EmployeePartiesValues entity);

    @Named("mapEmployeesToIds")
    default List<Long> mapEmployeesToIds(List<Employee> employees) {
        if (employees == null) {
            return null;
        }
        return employees.stream()
                .map(Employee::getId)
                .collect(Collectors.toList());
    }

    PartyResponseCalendarDTO toResponseCalendarDTO(Party party);
}
