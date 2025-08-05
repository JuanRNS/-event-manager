package com.example.eventmanagerbackend.infrastructure.services;

import com.example.eventmanagerbackend.domain.entities.Festa;
import com.example.eventmanagerbackend.domain.entities.FestaGarcom;
import com.example.eventmanagerbackend.domain.entities.Garcom;
import com.example.eventmanagerbackend.infrastructure.exceptions.FestaNotFoundException;
import com.example.eventmanagerbackend.infrastructure.repositories.FestaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FestaGarcomService {

    private final FestaRepository festaRepository;

    public FestaGarcomService(FestaRepository festaRepository) {
        this.festaRepository = festaRepository;
    }

    public void addGarcomToFesta(Long festaId, List<Long> garcomIds) {
        Festa festa = festaRepository.findById(festaId).orElseThrow(FestaNotFoundException::new);
        for (Long garcomId : garcomIds) {
            FestaGarcom festaGarcom = new FestaGarcom();
            Garcom garcom = new Garcom();
            garcom.setId(garcomId);
            festaGarcom.setFesta(festa);
            festaGarcom.setGarcom(garcom);
            festaGarcom.setValorDiariaGarcom(festa.getValorDiariaGarcom());
            festa.getFestaGarcoms().add(festaGarcom);
        }
        festaRepository.save(festa);

    }
}
