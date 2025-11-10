package com.example.eventmanagerbackend.infrastructure.config;

import com.example.eventmanagerbackend.domain.entities.Party;
import com.example.eventmanagerbackend.domain.enums.StatusParty;
import com.example.eventmanagerbackend.infrastructure.repositories.PartyRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class FestaScheduler {


    private final PartyRepository partyRepository;

    public FestaScheduler(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void updateFestas() {
        LocalDate now = LocalDate.now();
        List<Party> parties = partyRepository.findAllByStatus(StatusParty.AGENDADA);

        for (Party party : parties) {
            if (party.getDate().toLocalDate().isBefore(now) || party.getDate().toLocalDate().equals(now)) {
                party.setStatus(StatusParty.REALIZADA);
                partyRepository.save(party);
            }
        }
    }
}
