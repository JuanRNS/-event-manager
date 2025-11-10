package com.example.eventmanagerbackend.infrastructure.services;

import com.example.eventmanagerbackend.domain.dtos.MaterialRequestDTO;
import com.example.eventmanagerbackend.domain.dtos.MaterialResponseDTO;
import com.example.eventmanagerbackend.domain.entities.Material;
import com.example.eventmanagerbackend.domain.entities.User;
import com.example.eventmanagerbackend.infrastructure.exceptions.MaterialNotFoundException;
import com.example.eventmanagerbackend.infrastructure.mappers.MaterialMapper;
import com.example.eventmanagerbackend.infrastructure.repositories.MaterialRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;
    private final UserService userService;

    public MaterialService(MaterialRepository materialRepository, MaterialMapper materialMapper, UserService userService) {
        this.materialRepository = materialRepository;
        this.materialMapper = materialMapper;
        this.userService = userService;
    }

    public List<MaterialResponseDTO> getAllMaterials(Authentication authentication) {
        User user = userService.getUser(authentication);
        return materialRepository
                .findAllByUser(user)
                .stream().map(materialMapper::toMaterialResponseDTO)
                .toList();
    }

    public MaterialResponseDTO getMaterialById(Long id) {
        Material material = materialRepository.findById(id).orElseThrow(MaterialNotFoundException::new);
        return materialMapper.toMaterialResponseDTO(material);
    }

    public MaterialResponseDTO createMaterial(Authentication authentication, MaterialRequestDTO material) {
        if (material == null) {
            throw new MaterialNotFoundException();
        }
        User user = userService. getUser(authentication);
        Material newMaterial = new Material();
        newMaterial.setUser(user);
        newMaterial.setDescription(material.description());
        Material savedMaterial = materialRepository.save(newMaterial);
        return materialMapper.toMaterialResponseDTO(savedMaterial);
    }

    public void updateMaterial(Long id, MaterialRequestDTO materialRequest) {
        Material material = materialRepository.findById(id).orElseThrow(MaterialNotFoundException::new);
        material.setDescription(materialRequest.description());
        materialRepository.save(material);
    }

    public void deleteMaterial(Long id) {
        if (!materialRepository.existsById(id)) {
            throw new MaterialNotFoundException();
        }
        materialRepository.deleteById(id);
    }

}
