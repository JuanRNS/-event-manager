package com.example.eventmanagerbackend.infrastructure.controllers;

import com.example.eventmanagerbackend.domain.dtos.*;
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
    public ResponseEntity<Page<EmployeeIdResponseDTO>> getAllEmployee(Authentication authentication,Pageable pageable) {
        Page<EmployeeIdResponseDTO> employeeList = employeeService.getAllEmployee(authentication,pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeList);
    }

    @PostMapping("create")
    public ResponseEntity<Void> createEmployee(@RequestBody EmployeeRequestDTO employee, Authentication authentication) {
        employeeService.createEmployee(employee, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeIdResponseDTO> getEmployee(@PathVariable Long id) {
        EmployeeIdResponseDTO employee = employeeService.getEmployeeById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employee);
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
    public ResponseEntity<Page<EmployeeAddResponseDTO>> getEmployeeAdd(Authentication authentication,Pageable pageable) {
        Page<EmployeeAddResponseDTO> employeeList = employeeService.getEmployeeAddResponseDTO(authentication,pageable);
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
    public ResponseEntity<Page<EmployeeResponseDashboardDTO>> getEmployeeDashboardByPartyId(
            Authentication authentication,
            Pageable pageable,
            @RequestParam LocalDate fromDate,
            @RequestParam LocalDate toDate
    ) {
        Page<EmployeeResponseDashboardDTO> employeeList = employeeService.getEmployeeDashboardByDate(authentication,pageable, fromDate, toDate);
        return ResponseEntity.ok(employeeList);
    }

    @GetMapping("list/employee/party/{id}")
    public ResponseEntity<EmployeePartyDTO> getEmployeeParties(@PathVariable Long id) {
        EmployeePartyDTO employeeList = employeeService.getEmployeeParties(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeList);
    }

}
