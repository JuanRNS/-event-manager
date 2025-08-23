package com.example.eventmanagerbackend.infrastructure.controllers;

import com.example.eventmanagerbackend.domain.dtos.FestaGarcomRequestDTO;
import com.example.eventmanagerbackend.domain.dtos.FestaRequestDTO;
import com.example.eventmanagerbackend.domain.dtos.FestaResponseDTO;
import com.example.eventmanagerbackend.infrastructure.services.FestaGarcomService;
import com.example.eventmanagerbackend.infrastructure.services.FestaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("festa")
public class FestaController {

    private final FestaService festaService;
    private final FestaGarcomService festaGarcomService;

    public FestaController(FestaService festaService, FestaGarcomService festaGarcomService) {
        this.festaService = festaService;
        this.festaGarcomService = festaGarcomService;
    }

    @GetMapping("list")
    public ResponseEntity<Page<FestaResponseDTO>> getAll(Pageable pageable) {
        Page<FestaResponseDTO> festaList = festaService.getAllFestas(pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(festaList);
    }

    @PostMapping("create")
    public ResponseEntity<FestaResponseDTO> createFesta(@RequestBody FestaRequestDTO festa) {
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

    @PostMapping("adicionar-garcom")
    public ResponseEntity<Void> addGarcom(@RequestBody FestaGarcomRequestDTO festaGarcom) {
        festaGarcomService.addGarcomToFesta(festaGarcom.festaId(), festaGarcom.garcomIds());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
