package com.example.eventmanagerbackend.infrastructure.controllers;

import com.example.eventmanagerbackend.domain.dtos.*;
import com.example.eventmanagerbackend.infrastructure.services.PartyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/party")
public class PartyController {

    private final PartyService partyService;

    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    @GetMapping("list/status")
    public ResponseEntity<Page<PartyResponseDTO>> getAllFestasByStatus(Pageable pageable) {
        Page<PartyResponseDTO> festaList = partyService.getAllFestasByStatus(pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(festaList);
    }
    @GetMapping("list")
    public ResponseEntity<Page<PartyResponseDTO>> getAllFestas(Pageable pageable) {
        Page<PartyResponseDTO> festaList = partyService.getAllFestas(pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(festaList);
    }


    @PostMapping("create")
    public ResponseEntity<PartyResponseDTO> createFesta(@RequestBody PartyRequestDTO festa) {
        PartyResponseDTO createdFesta = partyService.createFesta(festa);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdFesta);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFesta(@PathVariable Long id) {
        partyService.deleteFesta(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateFesta(@PathVariable Long id, @RequestBody PartyUpdateRequestDTO festa) {
        partyService.updateFesta(id, festa);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<PartyResponseDTO> getFesta(@PathVariable Long id) {
        PartyResponseDTO festa = partyService.getFestaById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(festa);
    }

    @PostMapping("add-employee")
    public ResponseEntity<Void> addGarcom(@RequestBody PartyEmployeeAddRequestDTO partyEmployeeAddRequestDTO) {
        partyService.addEmployeeParty(partyEmployeeAddRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("status")
    public ResponseEntity<List<StatusResponseDTO>> getStatus() {
        return ResponseEntity.ok(partyService.getStatus());
    }

    @GetMapping("view/{id}")
    public ResponseEntity<PartyEmployeeViewDTO> getFestaGarcomById(@PathVariable Long id) {
        PartyEmployeeViewDTO partyEmployeeViewDTO = partyService.getFestaGarcomById(id);
        return ResponseEntity.ok(partyEmployeeViewDTO);
    }

    @PostMapping("{id}/add/values")
    public ResponseEntity<Void> addValuesInPary(@PathVariable Long id,@RequestBody EmployeePartiesValuesDTO employeePartiesValuesDTO) {
        partyService.createEmployeePartiesValues(id,employeePartiesValuesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}