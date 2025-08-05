package com.example.eventmanagerbackend.infrastructure.controllers;

import com.example.eventmanagerbackend.domain.dtos.GarcomRequestDTO;
import com.example.eventmanagerbackend.domain.dtos.GarcomResponseDTO;
import com.example.eventmanagerbackend.domain.entities.Garcom;
import com.example.eventmanagerbackend.infrastructure.services.GarcomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("garcom")
public class GarcomController {

    private final GarcomService garcomService;

    public GarcomController(GarcomService garcomService) {
        this.garcomService = garcomService;
    }

    @GetMapping("list")
    public ResponseEntity<List<GarcomResponseDTO>> getAll() {
        List<GarcomResponseDTO> garcomList = garcomService.getGarcoms();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(garcomList);
    }

    @PostMapping("create")
    public ResponseEntity<Garcom> createGarcom(GarcomRequestDTO garcom) {
        Garcom createdGarcom = garcomService.createGarcom(garcom);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdGarcom);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGarcom(@PathVariable Long id) {
        garcomService.deleteGarcom(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Garcom> getGarcom(@PathVariable Long id) {
        Garcom garcom = garcomService.getGarcomById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(garcom);
    }
}
