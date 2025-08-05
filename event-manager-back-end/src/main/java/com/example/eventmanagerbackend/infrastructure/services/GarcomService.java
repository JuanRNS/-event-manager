package com.example.eventmanagerbackend.infrastructure.services;

import com.example.eventmanagerbackend.domain.dtos.GarcomRequestDTO;
import com.example.eventmanagerbackend.domain.dtos.GarcomResponseDTO;
import com.example.eventmanagerbackend.domain.entities.Garcom;
import com.example.eventmanagerbackend.infrastructure.exceptions.GarcomNotFoundException;
import com.example.eventmanagerbackend.infrastructure.mappers.GarcomMapper;
import com.example.eventmanagerbackend.infrastructure.repositories.GarcomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarcomService {

    private final GarcomMapper garcomMapper;
    private final GarcomRepository garcomRepository;

    public GarcomService(GarcomRepository garcomRepository, GarcomMapper garcomMapper) {
        this.garcomRepository = garcomRepository;
        this.garcomMapper = garcomMapper;
    }

    public Garcom createGarcom(GarcomRequestDTO garcom) {
        Garcom garcomCreated = garcomMapper.toGarcom(garcom);
        return garcomRepository.save(garcomCreated);
    }

    public void deleteGarcom(Long id) {
        if (!garcomRepository.existsById(id)) {
            throw new GarcomNotFoundException();
        }
        garcomRepository.deleteById(id);
    }

    public Garcom getGarcomById(Long id) {
        return garcomRepository.findById(id).orElseThrow(GarcomNotFoundException::new);
    }

    public List<GarcomResponseDTO> getGarcoms() {
        List<Garcom> garcomList = garcomRepository.findAll();
        return garcomList
                .stream()
                .map(garcomMapper::toGarcomResponseDTO)
                .toList();
    }
}
