package com.example.eventmanagerbackend.infrastructure.controllers;

import com.example.eventmanagerbackend.domain.dtos.FestaRequestDTO;
import com.example.eventmanagerbackend.domain.dtos.FestaResponseDTO;
import com.example.eventmanagerbackend.infrastructure.services.FestaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("festa")
public class FestaController {

    private final FestaService festaService;

    public FestaController(FestaService festaService) {
        this.festaService = festaService;
    }

    @GetMapping("list")
    public ResponseEntity<List<FestaResponseDTO>> getAll() {
        List<FestaResponseDTO> festaList = festaService.getAllFestas();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(festaList);
    }

    @PostMapping("create")
    public ResponseEntity<FestaResponseDTO> createFesta(FestaRequestDTO festa) {
        FestaResponseDTO createdFesta = festaService.createFesta(festa);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdFesta);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFesta(@PathVariable Long id) {
        festaService.deleteFesta(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("{id}")
    public ResponseEntity<FestaResponseDTO> updateFesta(@PathVariable Long id, FestaRequestDTO festa) {
        FestaResponseDTO updatedFesta = festaService.updateFesta(id, festa);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedFesta);
    }

    @GetMapping("{id}")
    public ResponseEntity<FestaResponseDTO> getFesta(@PathVariable Long id) {
        FestaResponseDTO festa = festaService.getFestaById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(festa);
    }
}
