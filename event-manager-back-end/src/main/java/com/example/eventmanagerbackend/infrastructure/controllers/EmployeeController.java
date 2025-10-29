package com.example.eventmanagerbackend.infrastructure.controllers;

import com.example.eventmanagerbackend.domain.dtos.*;
import com.example.eventmanagerbackend.domain.entities.Garcom;
import com.example.eventmanagerbackend.infrastructure.services.FestaGarcomService;
import com.example.eventmanagerbackend.infrastructure.services.GarcomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/garcom")
public class GarcomController {

    private final GarcomService garcomService;
    private final FestaGarcomService festaGarcomService;

    public GarcomController(GarcomService garcomService, FestaGarcomService festaGarcomService) {
        this.garcomService = garcomService;
        this.festaGarcomService = festaGarcomService;
    }

    @GetMapping("options")
    public ResponseEntity<List<GarcomOptionsResponseDTO>> getOptions() {
        List<GarcomOptionsResponseDTO> garcomList = garcomService.getOptions();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(garcomList);
    }

    @GetMapping("list")
    public ResponseEntity<Page<GarcomResponseDTO>> getAllGarcoms(Pageable pageable) {
        Page<GarcomResponseDTO> garcomList = garcomService.getAllGarcoms(pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(garcomList);
    }

    @PostMapping("create")
    public ResponseEntity<Garcom> createGarcom(@RequestBody GarcomRequestDTO garcom) {
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
    public ResponseEntity<GarcomResponseDTO> getGarcom(@PathVariable Long id) {
        GarcomResponseDTO garcom = garcomService.getGarcomById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(garcom);
    }

    @GetMapping("status")
    public ResponseEntity<List<StatusResponseDTO>> getStatus() {
        List<StatusResponseDTO> status = garcomService.getStatus();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(status);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateGarcom(@PathVariable Long id, @RequestBody GarcomRequestDTO garcom) {
        garcomService.updateGarcom(id, garcom);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("list/dashboard")
    public ResponseEntity<Page<GarcomResponseDashboardDTO>> getGarcomDashboard(Pageable pageable) {
        Page<GarcomResponseDashboardDTO> garcomList = garcomService.getGarcomDashboard(pageable);
        return ResponseEntity.ok(garcomList);
    }

    @GetMapping("list/add")
    public ResponseEntity<Page<GarcomAddResponseDTO>> getGarcomAdd(Pageable pageable) {
        Page<GarcomAddResponseDTO> garcomList = garcomService.getGarcomAddResponseDTO(pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(garcomList);
    }
    @GetMapping("list/festa/{id}")
    public ResponseEntity<List<Long>> getGarcomIdsByFestaId(@PathVariable Long id) {
        List<Long> garcomIds = festaGarcomService.getGarcomIdsByFestaId(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(garcomIds);
    }

    @GetMapping("list/dashboard/date")
    public ResponseEntity<Page<GarcomResponseDashboardDTO>> getGarcomDashboardByFestaId(
            Pageable pageable,
            @RequestParam LocalDate fromDate,
            @RequestParam LocalDate toDate
    ) {
        Page<GarcomResponseDashboardDTO> garcomList = garcomService.getGarcomDashboardByDate(pageable, fromDate, toDate);
        return ResponseEntity.ok(garcomList);
    }

    @GetMapping("list/garcom/festas/{id}")
    public ResponseEntity<GarcomFestasDTO> getGarcomFestas(@PathVariable Long id, Pageable pageable) {
        GarcomFestasDTO garcomList = garcomService.getGarcomFestas(id, pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(garcomList);
    }

}
