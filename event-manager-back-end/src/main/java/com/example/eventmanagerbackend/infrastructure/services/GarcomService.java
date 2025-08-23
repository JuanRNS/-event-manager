package com.example.eventmanagerbackend.infrastructure.services;

import com.example.eventmanagerbackend.domain.dtos.GarcomOptionsResponseDTO;
import com.example.eventmanagerbackend.domain.dtos.GarcomRequestDTO;
import com.example.eventmanagerbackend.domain.dtos.GarcomResponseDTO;
import com.example.eventmanagerbackend.domain.dtos.StatusResponseDTO;
import com.example.eventmanagerbackend.domain.entities.Garcom;
import com.example.eventmanagerbackend.infrastructure.exceptions.GarcomNotFoundException;
import com.example.eventmanagerbackend.infrastructure.mappers.GarcomMapper;
import com.example.eventmanagerbackend.infrastructure.repositories.GarcomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public void updateGarcom(Long id, GarcomRequestDTO garcomUpdate) {
        Garcom garcom = garcomRepository.findById(id).orElseThrow(GarcomNotFoundException::new);
        updateGarcom(garcom, garcomUpdate);
    }

    public Page<GarcomResponseDTO > getAllGarcoms(Pageable pageable) {
        return garcomRepository
                .findAll(pageable)
                .map(garcomMapper::toGarcomResponseDTO);
    }

    public void deleteGarcom(Long id) {
        if (!garcomRepository.existsById(id)) {
            throw new GarcomNotFoundException();
        }
        garcomRepository.deleteById(id);
    }

    public GarcomResponseDTO getGarcomById(Long id) {
        Garcom garcom = garcomRepository.findById(id).orElseThrow(GarcomNotFoundException::new);
        return garcomMapper.toGarcomResponseDTO(garcom);
    }

    public List<GarcomOptionsResponseDTO> getOptions() {
        List<Garcom> garcomList = garcomRepository.findAll();
        return garcomList
                .stream()
                .map(garcomMapper::toGarcomOptionsResponseDTO)
                .toList();
    }

    public List<StatusResponseDTO> getStatus() {
        return List.of(
                new StatusResponseDTO("ATIVO", "ATIVO"),
                new StatusResponseDTO("INATIVO", "INATIVO")
        );
    }

    private void updateGarcom(Garcom garcom, GarcomRequestDTO garcomUpdate) {
        garcom.setName(garcomUpdate.name());
        garcom.setPhone(garcomUpdate.phone());
        garcom.setStatusGarcom(garcomUpdate.statusGarcom());
        garcom.setPixKey(garcomUpdate.pixKey());
        garcomRepository.save(garcom);
    }
}
