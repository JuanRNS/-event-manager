package com.example.eventmanagerbackend.infrastructure.services;

import com.example.eventmanagerbackend.domain.dtos.FestaRequestDTO;
import com.example.eventmanagerbackend.domain.dtos.FestaResponseDTO;
import com.example.eventmanagerbackend.domain.entities.Festa;
import com.example.eventmanagerbackend.domain.entities.Material;
import com.example.eventmanagerbackend.infrastructure.exceptions.FestaNotFoundException;
import com.example.eventmanagerbackend.infrastructure.exceptions.MaterialNotFoundException;
import com.example.eventmanagerbackend.infrastructure.mappers.FestaMapper;
import com.example.eventmanagerbackend.infrastructure.repositories.FestaRepository;
import com.example.eventmanagerbackend.infrastructure.repositories.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FestaService {

    private final FestaRepository festaRepository;
    private final MaterialRepository materialRepository;
    private final FestaMapper festaMapper;

    public FestaService(FestaRepository festaRepository, FestaMapper festaMapper, MaterialRepository materialRepository) {
        this.festaRepository = festaRepository;
        this.festaMapper = festaMapper;
        this.materialRepository = materialRepository;
    }

    public FestaResponseDTO createFesta(FestaRequestDTO festaRequestDTO) {
        Material material = new Material();
        material.setId(festaRequestDTO.idMaterial());
        Festa festa = festaMapper.toFesta(festaRequestDTO);
        festa.setMateriais(material);
        Festa savedFesta = festaRepository.save(festa);
        return festaMapper.toFestaResponseDTO(savedFesta);
    }

    public FestaResponseDTO getFestaById(Long id) {
        Festa festa = festaRepository.findById(id).orElseThrow(FestaNotFoundException::new);
        return new FestaResponseDTO(festa);
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

    public List<FestaResponseDTO> getAllFestas() {
        List<Festa> festas = festaRepository.findAll();
        return festas.stream()
                .map(festaMapper::toFestaResponseDTO)
                .toList();
    }


    private Festa parseFesta(Long id, FestaRequestDTO festaRequestDTO) {
        Festa festa = festaRepository.findById(id).orElseThrow(FestaNotFoundException::new);
        if(!Objects.equals(festa.getMateriais().getId(), festaRequestDTO.idMaterial())) {
            Material material = materialRepository.findById(festaRequestDTO.idMaterial()).orElseThrow(MaterialNotFoundException::new);
            festa.setMateriais(material);
        }
        festa.setLocal(festaRequestDTO.local());
        festa.setData(festaRequestDTO.data());
        festa.setValorDiariaGarcom(festaRequestDTO.valorDiariaGarcom());
        return festa;
    }
}
