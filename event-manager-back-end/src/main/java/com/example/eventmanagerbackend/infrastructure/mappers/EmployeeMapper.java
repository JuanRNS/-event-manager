package com.example.eventmanagerbackend.infrastructure.mappers;

import com.example.eventmanagerbackend.domain.dtos.*;
import com.example.eventmanagerbackend.domain.entities.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeOptionsResponseDTO toEmployeeOptionsResponseDTO(Employee employee);
    EmployeePartyResponseDTO toEmployeeResponseDTO(Employee employee);
    EmployeeAddResponseDTO toEmployeeAddResponseDTO(Employee employee);
    EmployeeIdResponseDTO toEmployeeIdResponseDTO(Employee employee);
}
