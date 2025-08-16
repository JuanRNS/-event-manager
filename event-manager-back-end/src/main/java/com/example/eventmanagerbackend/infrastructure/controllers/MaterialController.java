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
@RequestMapping("material")
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

    @GetMapping("list")
    public ResponseEntity<List<MaterialResponseDTO>> getAllMaterials() {
        List<MaterialResponseDTO> materialList = materialService.getAllMaterials();
        return new ResponseEntity<>(materialList, HttpStatus.OK);
    }
}
