package com.example.eventmanagerbackend.infrastructure.services;

import com.example.eventmanagerbackend.domain.dtos.*;
import com.example.eventmanagerbackend.domain.entities.Festa;
import com.example.eventmanagerbackend.domain.entities.Garcom;
import com.example.eventmanagerbackend.infrastructure.exceptions.GarcomNotFoundException;
import com.example.eventmanagerbackend.infrastructure.mappers.GarcomMapper;
import com.example.eventmanagerbackend.infrastructure.repositories.GarcomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GarcomService {

    private final GarcomMapper garcomMapper;
    private final GarcomRepository garcomRepository;
    private final FestaGarcomService festaGarcomService;

    public GarcomService(GarcomRepository garcomRepository, GarcomMapper garcomMapper, FestaGarcomService festaGarcomService) {
        this.garcomRepository = garcomRepository;
        this.garcomMapper = garcomMapper;
        this.festaGarcomService = festaGarcomService;
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

    public Page<GarcomResponseDashboardDTO> getGarcomDashboard(Pageable pageable) {
        Page<Garcom> garcomPage = garcomRepository.findAll(pageable);

        return garcomPage.map(garcom -> {
            List<Festa> festas = festaGarcomService.getFestaGarcomById(garcom.getId());
            festas.removeIf(festa -> !initialWeek(festa.getDate()));

            BigDecimal totalValue = festas.stream()
                    .map(Festa::getValuePerDay)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            return new GarcomResponseDashboardDTO(
                    garcom.getId(),
                    garcom.getName(),
                    (long) festas.size(),
                    totalValue
            );
        });
    }
    public Page<GarcomResponseDashboardDTO> getGarcomDashboardByDate(Pageable pageable, LocalDate fromDate, LocalDate toDate) {
        Page<Garcom> garcomPage = garcomRepository.findAll(pageable);

        return garcomPage.map(garcom -> {
            List<Festa> festas = festaGarcomService.getFestaGarcomById(garcom.getId());

            BigDecimal totalValue = festas.stream()
                    .filter(festa -> {
                        LocalDate date = festa.getDate().toLocalDate();
                        return (date.isEqual(fromDate) || date.isAfter(fromDate))
                                && (date.isEqual(toDate) || date.isBefore(toDate));
                    })
                    .map(Festa::getValuePerDay)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);


            return new GarcomResponseDashboardDTO(
                    garcom.getId(),
                    garcom.getName(),
                    (long) festas.size(),
                    totalValue
            );
        });
    }


    public Page<GarcomAddResponseDTO> getGarcomAddResponseDTO(Pageable pageable) {
        return garcomRepository.findAll(pageable)
                .map(garcomMapper::toGarcomAddResponseDTO);
    }

    public GarcomFestasDTO getGarcomFestas(Long id, Pageable pageable) {
        Optional<Garcom> garcom = garcomRepository.findById(id);
        if (garcom.isEmpty()) {
            throw new GarcomNotFoundException();
        }
        List<Festa> garcomFestas = festaGarcomService.getFestaGarcomById(id);
        List<FestasByGarcomIdDTO> festas = getFestasByGarcomIdDTOS(garcomFestas);

        return new GarcomFestasDTO(
                garcom.get().getName(),
                garcom.get().getPhone(),
                garcom.get().getPixKey(),
                festas
        );
    }


    private static List<FestasByGarcomIdDTO> getFestasByGarcomIdDTOS(List<Festa> garcomFestas) {
        List<FestasByGarcomIdDTO> festas = new ArrayList<>();
        for (Festa festa : garcomFestas) {
            festas.add(
                    new FestasByGarcomIdDTO(
                            festa.getLocation(),
                            festa.getNameClient(),
                            festa.getDate(),
                            festa.getValuePerDay(),
                            festa.getStatus(),
                            festa.getNumberOfPeople()
                    )
            );
        }
        return festas;
    }

    private void updateGarcom(Garcom garcom, GarcomRequestDTO garcomUpdate) {
        garcom.setName(garcomUpdate.name());
        garcom.setPhone(garcomUpdate.phone());
        garcom.setStatusGarcom(garcomUpdate.statusGarcom());
        garcom.setPixKey(garcomUpdate.pixKey());
        garcomRepository.save(garcom);
    }

    private boolean initialWeek(LocalDateTime todayDate){
        LocalDate todayDateParty = todayDate.toLocalDate();
        LocalDate today = LocalDate.now();
        LocalDate monday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate lastMonday = monday.minusWeeks(1);
        LocalDate lastSunday = lastMonday.plusDays(6);

        return !todayDateParty.isBefore(lastMonday) && !todayDateParty.isAfter(lastSunday);
    }
}
