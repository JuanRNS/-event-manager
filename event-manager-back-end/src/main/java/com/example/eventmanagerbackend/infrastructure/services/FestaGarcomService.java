package com.example.eventmanagerbackend.infrastructure.services;

import com.example.eventmanagerbackend.domain.entities.Festa;
import com.example.eventmanagerbackend.domain.entities.FestaGarcom;
import com.example.eventmanagerbackend.domain.entities.FestaGarcomId;
import com.example.eventmanagerbackend.domain.entities.Garcom;
import com.example.eventmanagerbackend.infrastructure.exceptions.FestaNotFoundException;
import com.example.eventmanagerbackend.infrastructure.repositories.FestaGarcomRepository;
import com.example.eventmanagerbackend.infrastructure.repositories.FestaRepository;
import com.example.eventmanagerbackend.infrastructure.repositories.GarcomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FestaGarcomService {

    private final FestaRepository festaRepository;
    private final GarcomRepository garcomRepository;
    private final FestaGarcomRepository festaGarcomRepository;

    public FestaGarcomService(FestaRepository festaRepository, GarcomRepository garcomRepository, FestaGarcomRepository festaGarcomRepository) {
        this.festaRepository = festaRepository;
        this.garcomRepository = garcomRepository;
        this.festaGarcomRepository = festaGarcomRepository;
    }

    public void addGarcomToFesta(Long festaId, List<Long> garcomIds) {
        Festa festa = festaRepository.findById(festaId).orElseThrow(FestaNotFoundException::new);
        for (Long garcomId : garcomIds) {
            FestaGarcom festaGarcom = new FestaGarcom();
            Garcom garcom = garcomRepository.findById(garcomId).orElseThrow();

            FestaGarcomId festaGarcomId = new FestaGarcomId();
            festaGarcomId.setFestaId(festa.getId());
            festaGarcomId.setGarcomId(garcom.getId());

            festaGarcom.setId(festaGarcomId);
            festaGarcom.setFesta(festa);
            festaGarcom.setGarcom(garcom);
            festaGarcom.setValorDiariaGarcom(festa.getValuePerDay());

            festa.getFestaGarcoms().add(festaGarcom);
        }
        festaRepository.save(festa);
    }

    public List<Festa> getFestaGarcomById(Long id) {
        return festaGarcomRepository.findFestasByGarcomId(id);
    }
}
