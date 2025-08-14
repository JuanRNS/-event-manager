package com.example.eventmanagerbackend.infrastructure.controllers;

import com.example.eventmanagerbackend.domain.dtos.MaterialRequestDTO;
import com.example.eventmanagerbackend.domain.entities.Material;
import com.example.eventmanagerbackend.infrastructure.services.MaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
