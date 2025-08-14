package com.example.eventmanagerbackend.infrastructure.services;

import com.example.eventmanagerbackend.domain.dtos.FestaRequestDTO;
import com.example.eventmanagerbackend.domain.dtos.FestaResponseDTO;
import com.example.eventmanagerbackend.domain.dtos.MaterialResponseDTO;
import com.example.eventmanagerbackend.domain.entities.*;
import com.example.eventmanagerbackend.infrastructure.exceptions.FestaNotFoundException;
import com.example.eventmanagerbackend.infrastructure.exceptions.MaterialNotFoundException;
import com.example.eventmanagerbackend.infrastructure.mappers.FestaMapper;
import com.example.eventmanagerbackend.infrastructure.repositories.FestaRepository;
import com.example.eventmanagerbackend.infrastructure.repositories.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        Material material = materialRepository.findById(festaRequestDTO.idMaterial()).orElseThrow(MaterialNotFoundException::new);
        Festa festa = festaMapper.toFesta(festaRequestDTO);
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

    public List<FestaResponseDTO> getAllFestas() {
        List<Festa> festas = festaRepository.findAll();
        return parseFestaResponseDTO(festas);
    }

    private FestaResponseDTO getFestaResponseDTO(Festa festa) {
        MaterialResponseDTO materialResponseDTO = parseMaterialResponseDTO(festa.getMaterial());
        List<Long> garcomId = festa.getFestaGarcoms()
                .stream()
                .map(festaGarcom -> festaGarcom.getGarcom().getId())
                .collect(Collectors.toList());
        return new FestaResponseDTO(
                festa.getId(),
                festa.getLocal(),
                festa.getNomeCliente(),
                festa.getData(),
                festa.getValorDiariaGarcom(),
                materialResponseDTO,
                garcomId
        );
    }

    private Festa parseFesta(Long id, FestaRequestDTO festaRequestDTO) {
        Festa festa = festaRepository.findById(id).orElseThrow(FestaNotFoundException::new);
        if(!Objects.equals(festa.getMaterial().getId(), festaRequestDTO.idMaterial())) {
            Material material = materialRepository.findById(festaRequestDTO.idMaterial()).orElseThrow(MaterialNotFoundException::new);
            festa.setMaterial(material);
        }
        festa.setLocal(festaRequestDTO.local());
        festa.setData(festaRequestDTO.data());
        festa.setValorDiariaGarcom(festaRequestDTO.valorDiariaGarcom());
        return festa;
    }

    private List<FestaResponseDTO> parseFestaResponseDTO(List<Festa> festas) {
        if (festas.isEmpty()) {
            return Collections.emptyList();
        }
        return festas.stream()
                .map(this::getFestaResponseDTO)
                .collect(Collectors.toList());
    }

    private MaterialResponseDTO parseMaterialResponseDTO(Material material) {
        return new MaterialResponseDTO(material.getId(), material.getDescricao());
    }
}
