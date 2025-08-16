package com.example.eventmanagerbackend.infrastructure.services;

import com.example.eventmanagerbackend.domain.dtos.MaterialRequestDTO;
import com.example.eventmanagerbackend.domain.dtos.MaterialResponseDTO;
import com.example.eventmanagerbackend.domain.entities.Material;
import com.example.eventmanagerbackend.infrastructure.exceptions.MaterialNotFoundException;
import com.example.eventmanagerbackend.infrastructure.mappers.MaterialMapper;
import com.example.eventmanagerbackend.infrastructure.repositories.MaterialRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    public MaterialService(MaterialRepository materialRepository, MaterialMapper materialMapper) {
        this.materialRepository = materialRepository;
        this.materialMapper = materialMapper;
    }

    public List<MaterialResponseDTO> getAllMaterials() {
        return materialRepository
                .findAll()
                .stream().map(materialMapper::toMaterialResponseDTO)
                .toList();
    }

    public Material createMaterial(MaterialRequestDTO material) {
        if (material == null) {
            throw new MaterialNotFoundException();
        }
        Material newMaterial = new Material();
        newMaterial.setDescricao(material.descricao());
        return materialRepository.save(newMaterial);
    }

    public Material updateMaterial(Long id, MaterialRequestDTO materialRequest) {
        Material material = materialRepository.findById(id).orElseThrow(MaterialNotFoundException::new);
        material.setDescricao(materialRequest.descricao());
        return materialRepository.save(material);
    }

    public void deleteMaterial(Long id) {
        if (!materialRepository.existsById(id)) {
            throw new MaterialNotFoundException();
        }
        materialRepository.deleteById(id);
    }

}
