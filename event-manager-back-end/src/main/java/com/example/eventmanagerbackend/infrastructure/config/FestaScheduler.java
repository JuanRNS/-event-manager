package com.example.eventmanagerbackend.infrastructure.config;

import com.example.eventmanagerbackend.domain.entities.Festa;
import com.example.eventmanagerbackend.domain.enums.StatusFesta;
import com.example.eventmanagerbackend.infrastructure.repositories.FestaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class FestaScheduler {


    private final FestaRepository festaRepository;

    public FestaScheduler(FestaRepository festaRepository) {
        this.festaRepository = festaRepository;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void updateFestas() {
        LocalDate now = LocalDate.now();
        List<Festa> festas = festaRepository.findAllByStatus(StatusFesta.AGENDADA);

        for (Festa festa : festas) {
            if (festa.getDate().toLocalDate().isBefore(now) || festa.getDate().toLocalDate().equals(now)) {
                festa.setStatus(StatusFesta.REALIZADA);
                festaRepository.save(festa);
            }
        }
    }
}
