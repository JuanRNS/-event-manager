package com.example.eventmanagerbackend.infrastructure.mappers;

import com.example.eventmanagerbackend.domain.dtos.EmployeeAddResponseDTO;
import com.example.eventmanagerbackend.domain.dtos.EmployeeOptionsResponseDTO;
import com.example.eventmanagerbackend.domain.dtos.EmployeeRequestDTO;
import com.example.eventmanagerbackend.domain.dtos.EmployeeResponseDTO;
import com.example.eventmanagerbackend.domain.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(target = "id", ignore = true)
    Employee toGarcom(EmployeeRequestDTO employeeRequestDTO);
    EmployeeOptionsResponseDTO toGarcomOptionsResponseDTO(Employee employee);
    EmployeeResponseDTO toGarcomResponseDTO(Employee employee);
    EmployeeAddResponseDTO toGarcomAddResponseDTO(Employee employee);
}
