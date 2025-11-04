package com.example.eventmanagerbackend.infrastructure.services;

import com.example.eventmanagerbackend.domain.dtos.EmployeeTypeRequestDTO;
import com.example.eventmanagerbackend.domain.dtos.EmployeeTypeResponseDTO;
import com.example.eventmanagerbackend.domain.entities.EmployeeType;
import com.example.eventmanagerbackend.domain.entities.User;
import com.example.eventmanagerbackend.infrastructure.exceptions.EmployeeTypeNotFoundException;
import com.example.eventmanagerbackend.infrastructure.repositories.EmployeeTypeRepository;
import com.example.eventmanagerbackend.infrastructure.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeTypeService {

    private final EmployeeTypeRepository employeeTypeRepository;
    private final UserService userService;

    public EmployeeTypeService(EmployeeTypeRepository employeeTypeRepository, UserService userRepository) {
        this.employeeTypeRepository = employeeTypeRepository;
        this.userService = userRepository;
    }

    public void saveEmployeeType(EmployeeTypeRequestDTO employeeTypeRequestDTO, Authentication authentication) {
        EmployeeType employeeType = new EmployeeType();
        User user = userService.getUser(authentication);
        employeeType.setUser(user);
        employeeType.setType(employeeTypeRequestDTO.type());
        employeeTypeRepository.save(employeeType);
    }

    public List<EmployeeTypeResponseDTO> getAllEmployeeTypes(Authentication authentication) {
        User user = userService.getUser(authentication);
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
}
