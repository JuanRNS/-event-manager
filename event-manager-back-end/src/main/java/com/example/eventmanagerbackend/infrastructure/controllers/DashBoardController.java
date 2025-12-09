package com.example.eventmanagerbackend.infrastructure.controllers;

import com.example.eventmanagerbackend.domain.dtos.DashBoardStatsResponseDTO;
import com.example.eventmanagerbackend.infrastructure.services.EmployeeService;
import com.example.eventmanagerbackend.infrastructure.services.PartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashBoardController {

    private final EmployeeService employeeService;
    private final PartyService partyService;

    @GetMapping("stats")
    public ResponseEntity<DashBoardStatsResponseDTO> getDashboardStats() {
        long totalEmployees = employeeService.countEmployeesByStatusEmployee();
        long totalParties = partyService.countPartiesByStatusAndUser();
        DashBoardStatsResponseDTO stats = new DashBoardStatsResponseDTO(totalEmployees, totalParties);
        return ResponseEntity.ok(stats);
    }
}
