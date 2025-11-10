package com.example.eventmanagerbackend.infrastructure.controllers;

import com.example.eventmanagerbackend.domain.dtos.*;
import com.example.eventmanagerbackend.infrastructure.services.PartyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<Page<PartyResponseDTO>> getAllPartiesByStatus(Authentication authentication, Pageable pageable) {
        Page<PartyResponseDTO> partyList = partyService.getAllPartiesByStatus(authentication,pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(partyList);
    }

    @GetMapping("list")
    public ResponseEntity<Page<PartyResponseDTO>> getAllParties(Pageable pageable) {
        Page<PartyResponseDTO> partyList = partyService.getAllParties(pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(partyList);
    }

    @PostMapping("create")
    public ResponseEntity<PartyResponseDTO> createParty(@RequestBody PartyRequestDTO party, Authentication authentication) {
        PartyResponseDTO createdParty = partyService.createParty(authentication,party);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdParty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteParty(@PathVariable Long id) {
        partyService.deleteParty(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateParty(@PathVariable Long id, @RequestBody PartyUpdateRequestDTO party) {
        partyService.updateParty(id, party);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<PartyResponseDTO> getParty(@PathVariable Long id) {
        PartyResponseDTO party = partyService.getPartyById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(party);
    }

    @PostMapping("add-employee")
    public ResponseEntity<Void> addEmployeeParty(@RequestBody PartyEmployeeAddRequestDTO partyEmployeeAddRequestDTO) {
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
    public ResponseEntity<PartyEmployeeViewDTO> getPartyEmployeeById(@PathVariable Long id) {
        PartyEmployeeViewDTO partyEmployeeViewDTO = partyService.getPartyEmployeeById(id);
        return ResponseEntity.ok(partyEmployeeViewDTO);
    }

    @PostMapping("{id}/add/values")
    public ResponseEntity<Void> addValuesInParty(@PathVariable Long id, @RequestBody EmployeePartiesValuesDTO employeePartiesValuesDTO) {
        partyService.createEmployeePartiesValues(id, employeePartiesValuesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
