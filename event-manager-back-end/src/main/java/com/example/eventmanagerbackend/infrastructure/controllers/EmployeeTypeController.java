package com.example.eventmanagerbackend.infrastructure.controllers;

import com.example.eventmanagerbackend.domain.dtos.EmployeeTypeRequestDTO;
import com.example.eventmanagerbackend.domain.dtos.EmployeeTypeResponseDTO;
import com.example.eventmanagerbackend.infrastructure.services.EmployeeTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/type")
public class EmployeeTypeController {

    private final EmployeeTypeService employeeTypeService;

    public EmployeeTypeController(EmployeeTypeService employeeTypeService) {
        this.employeeTypeService = employeeTypeService;
    }

    @PostMapping("create")
    public ResponseEntity<Void> createEmployeeType(@RequestBody EmployeeTypeRequestDTO employeeTypeRequestDTO, Authentication authentication) {
        employeeTypeService.saveEmployeeType(employeeTypeRequestDTO, authentication);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("options/employee-type")
    public ResponseEntity<List<EmployeeTypeResponseDTO>> getOptions(Authentication authentication) {
        List<EmployeeTypeResponseDTO> employeeTypeList = employeeTypeService.getAllEmployeeTypes(authentication);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeTypeList);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmployeeType(@PathVariable Long id) {
        employeeTypeService.deleteEmployeeType(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateEmployeeType(
            @PathVariable Long id,
            @RequestBody EmployeeTypeRequestDTO employeeTypeRequestDTO
    ) {
        employeeTypeService.updateEmployeeType(id,employeeTypeRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
