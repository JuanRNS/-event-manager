package com.example.eventmanagerbackend.infrastructure.services;

import com.example.eventmanagerbackend.domain.dtos.*;
import com.example.eventmanagerbackend.domain.entities.*;
import com.example.eventmanagerbackend.domain.enums.StatusFesta;
import com.example.eventmanagerbackend.infrastructure.exceptions.FestaNotFoundException;
import com.example.eventmanagerbackend.infrastructure.exceptions.MaterialNotFoundException;
import com.example.eventmanagerbackend.infrastructure.mappers.FestaMapper;
import com.example.eventmanagerbackend.infrastructure.repositories.FestaRepository;
import com.example.eventmanagerbackend.infrastructure.repositories.MaterialRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FestaService {

    private final FestaRepository festaRepository;
    private final MaterialRepository materialRepository;
    private final FestaMapper festaMapper;
    private final GarcomService garcomService;
    public FestaService(FestaRepository festaRepository, FestaMapper festaMapper, MaterialRepository materialRepository, GarcomService garcomService) {
        this.festaRepository = festaRepository;
        this.festaMapper = festaMapper;
        this.materialRepository = materialRepository;
        this.garcomService = garcomService;
    }

    public FestaResponseDTO createFesta(FestaRequestDTO festaRequestDTO) {
        Material material = materialRepository.findById(festaRequestDTO.idMaterial()).orElseThrow(MaterialNotFoundException::new);
        Festa festa = parseFesta(festaRequestDTO);
        festa.setMaterial(material);
        Festa savedFesta = festaRepository.save(festa);
        return festaMapper.toFestaResponseDTO(savedFesta);
    }

    public FestaResponseDTO getFestaById(Long id) {
        Festa festa = festaRepository.findById(id).orElseThrow(FestaNotFoundException::new);
        return getFestaResponseDTO(festa);
    }

    public FestaResponseDTO updateFesta(Long id, FestaRequestDTO festaRequestDTO) {
        Festa festa = parseFesta(id, festaRequestDTO);
        Festa updatedFesta = festaRepository.save(festa);
        return festaMapper.toFestaResponseDTO(updatedFesta);
    }

    public void deleteFesta(Long id) {
        if (!festaRepository.existsById(id)) {
            throw new FestaNotFoundException();
        }
        festaRepository.deleteById(id);
    }

    public Page<FestaResponseDTO> getAllFestasByStatus(Pageable pageable) {
        Page<Festa> festas = festaRepository.findAllByStatus(StatusFesta.AGENDADA, pageable);
        return parseFestaResponseDTO(festas);
    }

    public Page<FestaResponseDTO> getAllFestas(Pageable pageable) {
        Page<Festa> festas = festaRepository.findAll(pageable);
        return parseFestaResponseDTO(festas);
    }

    public List<StatusResponseDTO> getStatus() {
       return List.of(
               new StatusResponseDTO("REALIZADA", "REALIZADA"),
               new StatusResponseDTO("CANCELADA", "CANCELADA"),
               new StatusResponseDTO("AGENDADA", "AGENDADA")
       );
    }

    public FestaGarcomViewDTO getFestaGarcomById(Long idParty) {
        Festa festa = festaRepository.findById(idParty).orElseThrow(FestaNotFoundException::new);
        List<Long> garcomId = festa.getFestaGarcoms()
                .stream()
                .map(festaGarcom -> festaGarcom.getGarcom().getId())
                .toList();
        List<GarcomResponseDTO> garcomList = new ArrayList<>();
        for (Long id : garcomId) {
            GarcomResponseDTO garcom = garcomService.getGarcomById(id);
            garcomList.add(garcom);
        }
        return new FestaGarcomViewDTO(
                festa.getId(),
                festa.getLocation(),
                festa.getNameClient(),
                festa.getDate(),
                festa.getValuePerDay(),
                festa.getMaterial(),
                festa.getNumberOfPeople(),
                garcomList,
                festa.getStatus()
        );
    }

    private Festa parseFesta(FestaRequestDTO festaRequestDTO) {
        Festa festa = new Festa();
        festa.setLocation(festaRequestDTO.location());
        festa.setNameClient(festaRequestDTO.nameClient());
        festa.setDate(festaRequestDTO.date());
        festa.setValuePerDay(festaRequestDTO.valuePerDay());
        festa.setNumberOfPeople(festaRequestDTO.numberOfPeople());
        return festa;
    }

    private FestaResponseDTO getFestaResponseDTO(Festa festa) {
        MaterialResponseDTO materialResponseDTO = parseMaterialResponseDTO(festa.getMaterial());
        List<Long> garcomId = festa.getFestaGarcoms()
                .stream()
                .map(festaGarcom -> festaGarcom.getGarcom().getId())
                .collect(Collectors.toList());
        return new FestaResponseDTO(
                festa.getId(),
                festa.getLocation(),
                festa.getNameClient(),
                festa.getDate(),
                festa.getValuePerDay(),
                materialResponseDTO,
                garcomId,
                festa.getNumberOfPeople(),
                festa.getStatus()
        );
    }

    private Festa parseFesta(Long id, FestaRequestDTO festaRequestDTO) {
        Festa festa = festaRepository.findById(id).orElseThrow(FestaNotFoundException::new);
        if(!Objects.equals(festa.getMaterial().getId(), festaRequestDTO.idMaterial())) {
            Material material = materialRepository.findById(festaRequestDTO.idMaterial()).orElseThrow(MaterialNotFoundException::new);
            festa.setMaterial(material);
        }
        festa.setLocation(festaRequestDTO.location());
        festa.setDate(festaRequestDTO.date());
        festa.setValuePerDay(festaRequestDTO.valuePerDay());
        return festa;
    }

    private Page<FestaResponseDTO> parseFestaResponseDTO(Page<Festa> festas) {
        if (festas.isEmpty()) {
            return Page.empty();
        }
        return festas.map(this::getFestaResponseDTO);
    }

    private MaterialResponseDTO parseMaterialResponseDTO(Material material) {
        if (material == null) {
            return null;
        }
        return new MaterialResponseDTO(material.getId(), material.getDescription());
    }
}
