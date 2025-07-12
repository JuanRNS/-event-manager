package com.example.eventmanagerbackend.domain.infrastructure.services;

import com.example.eventmanagerbackend.domain.dtos.FestaRequestDTO;
import com.example.eventmanagerbackend.domain.dtos.FestaResponseDTO;
import com.example.eventmanagerbackend.domain.entities.Festa;
import com.example.eventmanagerbackend.domain.entities.Material;
import com.example.eventmanagerbackend.domain.infrastructure.repositories.FestaRepository;
import org.springframework.stereotype.Service;

@Service
public class FestaService {

    private final FestaRepository festaRepository;

    public FestaService(FestaRepository festaRepository) {
        this.festaRepository = festaRepository;
    }

    public FestaResponseDTO createFesta(FestaRequestDTO festaRequestDTO) {
        Material material = new Material();
        material.setId(festaRequestDTO.idMaterial());
        Festa festa = new Festa(
                festaRequestDTO.nomeCliente(),
                festaRequestDTO.local(),
                festaRequestDTO.data(),
                festaRequestDTO.valorDiariaGarcom(),
                material
        );
        Festa savedFesta = festaRepository.save(festa);
        return new FestaResponseDTO(savedFesta);
    }

    public FestaResponseDTO getFestaById(Long id) {
        Festa festa = festaRepository.findById(id).orElseThrow(() -> new RuntimeException("Festa não encontrada"));
        return new FestaResponseDTO(festa);
    }

    public FestaResponseDTO updateFesta(Long id, FestaRequestDTO festaRequestDTO) {
        Festa festa = festaRepository.findById(id).orElseThrow(() -> new RuntimeException("Festa não encontrada"));
        if(festa.getMateriais().getId() == festaRequestDTO.idMaterial()) {
            Material material = new Material();
            material.setId(festaRequestDTO.idMaterial());
            festa.setMateriais(material);
        }
        festa.setLocal(festaRequestDTO.local());
        festa.setData(festaRequestDTO.data());
        festa.setValorDiariaGarcom(festaRequestDTO.valorDiariaGarcom());
        Festa updatedFesta = festaRepository.save(festa);
        return new FestaResponseDTO(updatedFesta);
    }

    public void deleteFesta(Long id) {
        festaRepository.deleteById(id);
    }

}
