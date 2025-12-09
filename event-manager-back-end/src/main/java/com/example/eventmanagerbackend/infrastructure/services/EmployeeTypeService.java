package com.example.eventmanagerbackend.infrastructure.services;

import com.example.eventmanagerbackend.domain.dtos.EmployeeTypeRequestDTO;
import com.example.eventmanagerbackend.domain.dtos.EmployeeTypeResponseDTO;
import com.example.eventmanagerbackend.domain.entities.EmployeeType;
import com.example.eventmanagerbackend.domain.entities.User;
import com.example.eventmanagerbackend.infrastructure.exceptions.EmployeeTypeNotFoundException;
import com.example.eventmanagerbackend.infrastructure.repositories.EmployeeTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeTypeService {

    private final EmployeeTypeRepository employeeTypeRepository;
    private final UserService userService;

    public void saveEmployeeType(EmployeeTypeRequestDTO employeeTypeRequestDTO) {
        EmployeeType employeeType = new EmployeeType();
        User user = userService.getCurrentUser();
        employeeType.setUser(user);
        employeeType.setType(employeeTypeRequestDTO.type());
        employeeTypeRepository.save(employeeType);
    }

    public List<EmployeeTypeResponseDTO> getAllEmployeeTypes() {
        User user = userService.getCurrentUser();
        return employeeTypeRepository
                .findByUser(user)
                .stream()
                .map(employeeType -> new EmployeeTypeResponseDTO(
                        employeeType.getId(),
                        employeeType.getType()
                    )
                )
                .toList();
    }

    public void deleteEmployeeType(Long id) {
        employeeTypeRepository.deleteById(id);
    }

    public void updateEmployeeType(Long id, EmployeeTypeRequestDTO employeeTypeRequestDTO) {
        EmployeeType employeeType = employeeTypeRepository.findById(id)
                .orElseThrow(EmployeeTypeNotFoundException::new);
        employeeType.setType(employeeTypeRequestDTO.type());
        employeeTypeRepository.save(employeeType);
    }

    public EmployeeType getEmployeeTypeById(Long id) {
        return employeeTypeRepository.findById(id)
                .orElseThrow(EmployeeTypeNotFoundException::new);
    }
}
