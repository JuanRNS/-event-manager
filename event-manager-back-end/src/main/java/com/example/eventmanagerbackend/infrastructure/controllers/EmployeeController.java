package com.example.eventmanagerbackend.infrastructure.controllers;

import com.example.eventmanagerbackend.domain.dtos.*;
import com.example.eventmanagerbackend.domain.entities.Employee;
import com.example.eventmanagerbackend.infrastructure.services.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("options")
    public ResponseEntity<List<EmployeeOptionsResponseDTO>> getOptions() {
        List<EmployeeOptionsResponseDTO> garcomList = employeeService.getOptions();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(garcomList);
    }

    @GetMapping("list")
    public ResponseEntity<Page<EmployeeResponseDTO>> getAllEmployee(Authentication authentication,Pageable pageable) {
        Page<EmployeeResponseDTO> garcomList = employeeService.getAllEmployee(authentication,pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(garcomList);
    }

    @PostMapping("create")
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeRequestDTO employee, Authentication authentication) {
        Employee createdEmployee = employeeService.createEmployee(employee, authentication);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdEmployee);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployee(@PathVariable Long id) {
        EmployeeResponseDTO garcom = employeeService.getEmployeeById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(garcom);
    }

    @GetMapping("status")
    public ResponseEntity<List<StatusResponseDTO>> getStatus() {
        List<StatusResponseDTO> status = employeeService.getStatus();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(status);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequestDTO employee) {
        employeeService.updateEmployee(id, employee);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("list/dashboard")
    public ResponseEntity<Page<EmployeeResponseDashboardDTO>> getEmployeeDashboard(Authentication authentication,Pageable pageable) {
        Page<EmployeeResponseDashboardDTO> employeeList = employeeService.getEmployeeDashboard(authentication,pageable);
        return ResponseEntity.ok(employeeList);
    }

    @GetMapping("list/add")
    public ResponseEntity<Page<EmployeeAddResponseDTO>> getEmployeeAdd(Pageable pageable) {
        Page<EmployeeAddResponseDTO> employeeList = employeeService.getGarcomAddResponseDTO(pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeList);
    }
    @GetMapping("list/party/{id}")
    public ResponseEntity<List<Long>> getEmployeeIdsByPartyId(@PathVariable Long id) {
        List<Long> EmployeeIds = employeeService.getEmployeeByPartyId(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(EmployeeIds);
    }

    @GetMapping("list/dashboard/date")
    public ResponseEntity<Page<EmployeeResponseDashboardDTO>> getEmployeeDashboardByFestaId(
            Authentication authentication,
            Pageable pageable,
            @RequestParam LocalDate fromDate,
            @RequestParam LocalDate toDate
    ) {
        Page<EmployeeResponseDashboardDTO> employeeList = employeeService.getEmployeeDashboardByDate(authentication,pageable, fromDate, toDate);
        return ResponseEntity.ok(employeeList);
    }

    @GetMapping("list/employee/party/{id}")
    public ResponseEntity<EmployeePartyDTO> getEmployeeFestas(@PathVariable Long id, Pageable pageable) {
        EmployeePartyDTO employeeList = employeeService.getEmployeeFestas(id, pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeList);
    }

}
