package com.example.eventmanagerbackend.infrastructure.services;

import com.example.eventmanagerbackend.domain.dtos.MaterialRequestDTO;
import com.example.eventmanagerbackend.domain.entities.Material;
import com.example.eventmanagerbackend.infrastructure.exceptions.MaterialNotFoundException;
import com.example.eventmanagerbackend.infrastructure.repositories.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    public Material createMaterial(MaterialRequestDTO material) {
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
