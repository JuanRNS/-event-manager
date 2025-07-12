package com.example.eventmanagerbackend.domain.infrastructure.services;

import com.example.eventmanagerbackend.domain.entities.Festa;
import com.example.eventmanagerbackend.domain.entities.Garcom;
import com.example.eventmanagerbackend.domain.infrastructure.repositories.GarcomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarcomService {

    private final GarcomRepository garcomRepository;

    public GarcomService(GarcomRepository garcomRepository) {
        this.garcomRepository = garcomRepository;
    }

    public void deleteGarcom(Long id) {
        garcomRepository.deleteById(id);
    }

    public Garcom getGarcom(Long id) {
        return garcomRepository.findById(id).orElseThrow(() -> new RuntimeException("Garcom não encontrado"));
    }

    public List<Garcom> getGarcoms() {
        return garcomRepository.findAll();
    }
}
