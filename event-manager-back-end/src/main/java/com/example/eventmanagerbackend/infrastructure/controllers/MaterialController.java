package com.example.eventmanagerbackend.infrastructure.controllers;

import com.example.eventmanagerbackend.domain.dtos.MaterialRequestDTO;
import com.example.eventmanagerbackend.domain.dtos.MaterialResponseDTO;
import com.example.eventmanagerbackend.domain.entities.Material;
import com.example.eventmanagerbackend.infrastructure.services.MaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/material")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping("create")
    public ResponseEntity<Material> createMaterial(@RequestBody MaterialRequestDTO materialRequest) {
        Material material = materialService.createMaterial(materialRequest);
        return new ResponseEntity<>(material, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<MaterialResponseDTO> getMaterialById(@PathVariable Long id) {
        MaterialResponseDTO materialResponseDTO = materialService.getMaterialById(id);
        return new ResponseEntity<>(materialResponseDTO, HttpStatus.OK);
    }

    @GetMapping("list")
    public ResponseEntity<List<MaterialResponseDTO>> getAllMaterials() {
        List<MaterialResponseDTO> materialList = materialService.getAllMaterials();
        return new ResponseEntity<>(materialList, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateMaterial(@PathVariable Long id, @RequestBody MaterialRequestDTO materialRequest) {
        materialService.updateMaterial(id, materialRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Long id) {
        materialService.deleteMaterial(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
