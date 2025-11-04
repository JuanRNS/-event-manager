package com.example.eventmanagerbackend.infrastructure.services;

import com.example.eventmanagerbackend.domain.dtos.*;
import com.example.eventmanagerbackend.domain.entities.*;
import com.example.eventmanagerbackend.domain.enums.StatusFesta;
import com.example.eventmanagerbackend.infrastructure.exceptions.EmployeeNotFoundException;
import com.example.eventmanagerbackend.infrastructure.exceptions.EmployeeTypeNotFoundException;
import com.example.eventmanagerbackend.infrastructure.exceptions.PartyNotFoundException;
import com.example.eventmanagerbackend.infrastructure.exceptions.MaterialNotFoundException;
import com.example.eventmanagerbackend.infrastructure.mappers.PartyMapper;
import com.example.eventmanagerbackend.infrastructure.repositories.EmployeeRepository;
import com.example.eventmanagerbackend.infrastructure.repositories.EmployeeTypeRepository;
import com.example.eventmanagerbackend.infrastructure.repositories.PartyRepository;
import com.example.eventmanagerbackend.infrastructure.repositories.MaterialRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PartyService {

    private final PartyRepository partyRepository;
    private final MaterialRepository materialRepository;
    private final PartyMapper partyMapper;
    private final EmployeeService employeeService;
    private final EmployeeTypeRepository employeeTypeRepository;
    private final EmployeeRepository employeeRepository;
    public PartyService(
            PartyRepository partyRepository,
            PartyMapper partyMapper,
            MaterialRepository materialRepository,
            EmployeeService employeeService,
            EmployeeTypeRepository employeeTypeRepository,
            EmployeeRepository employeeRepository
    ) {
        this.partyRepository = partyRepository;
        this.partyMapper = partyMapper;
        this.materialRepository = materialRepository;
        this.employeeService = employeeService;
        this.employeeTypeRepository = employeeTypeRepository;
        this.employeeRepository = employeeRepository;
    }

    public PartyResponseDTO createFesta(PartyRequestDTO partyRequestDTO) {
        Material material = materialRepository.findById(partyRequestDTO.idMaterial()).orElseThrow(MaterialNotFoundException::new);
        Party party = parseFesta(partyRequestDTO);
        party.setMaterial(material);
        Party savedParty = partyRepository.save(party);
        return partyMapper.toFestaResponseDTO(savedParty);
    }

    public void createEmployeePartiesValues(Long idParty,EmployeePartiesValuesDTO employeePartiesValuesDTO){
        Party party = partyRepository.findById(idParty).orElseThrow(PartyNotFoundException::new);
        EmployeeType employeeType = employeeTypeRepository.findById(employeePartiesValuesDTO.idEmployeeType()).orElseThrow(EmployeeTypeNotFoundException::new);
        EmployeePartiesValues employeePartiesValues = new EmployeePartiesValues(employeeType,party,employeePartiesValuesDTO.value());
        party.addValues(employeePartiesValues);
        partyRepository.save(party);
    }

    public PartyResponseDTO getFestaById(Long id) {
        Party party = partyRepository.findById(id).orElseThrow(PartyNotFoundException::new);
        return getFestaResponseDTO(party);
    }

    public void updateFesta(Long id, PartyUpdateRequestDTO festaRequestDTO) {
        Party party = parseUpdateFesta(id, festaRequestDTO);
        partyRepository.save(party);
    }

    public void deleteFesta(Long id) {
        if (!partyRepository.existsById(id)) {
            throw new PartyNotFoundException();
        }
        partyRepository.deleteById(id);
    }

    public Page<PartyResponseDTO> getAllFestasByStatus(Pageable pageable) {
        Page<Party> festas = partyRepository.findAllByStatus(StatusFesta.AGENDADA, pageable);
        return parseFestaResponseDTO(festas);
    }

    public Page<PartyResponseDTO> getAllFestas(Pageable pageable) {
        Page<Party> festas = partyRepository.findAll(pageable);
        return parseFestaResponseDTO(festas);
    }

    public List<StatusResponseDTO> getStatus() {
       return List.of(
               new StatusResponseDTO("REALIZADA", "REALIZADA"),
               new StatusResponseDTO("CANCELADA", "CANCELADA"),
               new StatusResponseDTO("AGENDADA", "AGENDADA")
       );
    }

    public PartyEmployeeViewDTO getFestaGarcomById(Long idParty) {
        Party party = partyRepository.findById(idParty).orElseThrow(PartyNotFoundException::new);
        List<Long> employeeId = party.getPartyEmployees()
                .stream()
                .map(Employee::getId)
                .toList();
        List<EmployeeResponseDTO> employeeList = new ArrayList<>();
        for (Long id : employeeId) {
            EmployeeResponseDTO employee = employeeService.getEmployeeById(id);
            employeeList.add(employee);
        }
        return new PartyEmployeeViewDTO(
                party.getId(),
                party.getLocation(),
                party.getNameClient(),
                party.getDate(),
                party.getValues(),
                party.getMaterial(),
                party.getNumberOfPeople(),
                employeeList,
                party.getStatus()
        );
    }

    public void addEmployeeParty(PartyEmployeeAddRequestDTO partyEmployeeAddRequestDTO) {
        Party party = partyRepository.findById(partyEmployeeAddRequestDTO.partyId()).orElseThrow(PartyNotFoundException::new);
        List<Employee> employeeList = parseEmployeeIds(partyEmployeeAddRequestDTO.employeeIds());
        party.setPartyEmployees(employeeList);
        partyRepository.save(party);
    }

    private List<Employee> parseEmployeeIds(List<Long> ids) {
        List<Employee> employeeList = new ArrayList<>();
        for (Long id : ids) {
            Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
            employeeList.add(employee);
        }
        return employeeList;
    }

    private Party parseFesta(PartyRequestDTO partyRequestDTO) {
        Party party = new Party();
        party.setLocation(partyRequestDTO.location());
        party.setNameClient(partyRequestDTO.nameClient());
        party.setDate(partyRequestDTO.date());
        party.setNumberOfPeople(partyRequestDTO.numberOfPeople());
        return party;
    }

    private PartyResponseDTO getFestaResponseDTO(Party party) {
        MaterialResponseDTO materialResponseDTO = parseMaterialResponseDTO(party.getMaterial());
        List<Long> garcomId = party.getPartyEmployees()
                .stream()
                .map(Employee::getId)
                .collect(Collectors.toList());
        return new PartyResponseDTO(
                party.getId(),
                party.getLocation(),
                party.getNameClient(),
                party.getDate(),
                party.getValues(),
                materialResponseDTO,
                garcomId,
                party.getNumberOfPeople(),
                party.getStatus()
        );
    }

    private Party parseUpdateFesta(Long id, PartyUpdateRequestDTO festaRequestDTO) {
        Party party = partyRepository.findById(id).orElseThrow(PartyNotFoundException::new);
        if(!Objects.equals(party.getMaterial().getId(), festaRequestDTO.idMaterial())) {
            Material material = materialRepository.findById(festaRequestDTO.idMaterial()).orElseThrow(MaterialNotFoundException::new);
            party.setMaterial(material);
        }
        party.setLocation(festaRequestDTO.location());
        party.setDate(festaRequestDTO.date());
        party.setNameClient(festaRequestDTO.nameClient());
        party.setNumberOfPeople(festaRequestDTO.numberOfPeople());
        party.setStatus(festaRequestDTO.status());
        return party;
    }

    private Page<PartyResponseDTO> parseFestaResponseDTO(Page<Party> festas) {
        if (festas.isEmpty()) {
            return Page.empty();
        }
        return festas.map(this::getFestaResponseDTO);
    }

    private MaterialResponseDTO parseMaterialResponseDTO(Material material) {
        if (material == null) {
            return null;
        }
        return new MaterialResponseDTO(material.getId(), material.getDescription());
    }
}
