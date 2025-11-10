package com.example.eventmanagerbackend.infrastructure.services;

import com.example.eventmanagerbackend.domain.dtos.*;
import com.example.eventmanagerbackend.domain.entities.*;
import com.example.eventmanagerbackend.domain.enums.StatusParty;
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
import org.springframework.security.core.Authentication;
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
    private final UserService userService;
    public PartyService(
            PartyRepository partyRepository,
            PartyMapper partyMapper,
            MaterialRepository materialRepository,
            EmployeeService employeeService,
            EmployeeTypeRepository employeeTypeRepository,
            EmployeeRepository employeeRepository,
            UserService userService
    ) {
        this.partyRepository = partyRepository;
        this.partyMapper = partyMapper;
        this.materialRepository = materialRepository;
        this.employeeService = employeeService;
        this.employeeTypeRepository = employeeTypeRepository;
        this.employeeRepository = employeeRepository;
        this.userService = userService;
    }

    public PartyResponseDTO createParty(Authentication authentication,PartyRequestDTO partyRequestDTO) {
        Material material = materialRepository.findById(partyRequestDTO.idMaterial()).orElseThrow(MaterialNotFoundException::new);
        User user = userService.getUser(authentication);
        Party party = parseParty(partyRequestDTO);
        party.setMaterial(material);
        party.setUser(user);
        Party savedParty = partyRepository.save(party);
        return partyMapper.toPartyResponseDTO(savedParty);
    }

    public void createEmployeePartiesValues(Long idParty,EmployeePartiesValuesDTO employeePartiesValuesDTO){
        Party party = partyRepository.findById(idParty).orElseThrow(PartyNotFoundException::new);
        EmployeeType employeeType = employeeTypeRepository.findById(employeePartiesValuesDTO.idEmployeeType()).orElseThrow(EmployeeTypeNotFoundException::new);
        EmployeePartiesValues employeePartiesValues = new EmployeePartiesValues(employeeType,party,employeePartiesValuesDTO.value());
        party.addValues(employeePartiesValues);
        partyRepository.save(party);
    }

    public PartyResponseDTO getPartyById(Long id) {
        Party party = partyRepository.findById(id).orElseThrow(PartyNotFoundException::new);
        return getPartyResponseDTO(party);
    }

    public void updateParty(Long id, PartyUpdateRequestDTO partyRequestDTO) {
        Party party = parseUpdateParty(id, partyRequestDTO);
        partyRepository.save(party);
    }

    public void deleteParty(Long id) {
        if (!partyRepository.existsById(id)) {
            throw new PartyNotFoundException();
        }
        partyRepository.deleteById(id);
    }

    public Page<PartyResponseDTO> getAllPartiesByStatus(Authentication authentication, Pageable pageable) {
        User user = userService.getUser(authentication);
        Page<Party> parties = partyRepository.findAllByUserAndStatus(user,StatusParty.AGENDADA, pageable);
        return parsePartyResponseDTO(parties);
    }

    public Page<PartyResponseDTO> getAllParties(Pageable pageable) {
        Page<Party> parties = partyRepository.findAll(pageable);
        return parsePartyResponseDTO(parties);
    }

    public List<StatusResponseDTO> getStatus() {
        return List.of(
                new StatusResponseDTO("REALIZADA", "REALIZADA"),
                new StatusResponseDTO("CANCELADA", "CANCELADA"),
                new StatusResponseDTO("AGENDADA", "AGENDADA")
        );
    }

    public PartyEmployeeViewDTO getPartyEmployeeById(Long idParty) {
        Party party = partyRepository.findById(idParty).orElseThrow(PartyNotFoundException::new);
        List<Long> employeeId = party.getPartyEmployees()
                .stream()
                .map(Employee::getId)
                .toList();
        List<EmployeeIdResponseDTO> employeeList = new ArrayList<>();
        for (Long id : employeeId) {
            EmployeeIdResponseDTO employee = employeeService.getEmployeeById(id);
            employeeList.add(employee);
        }
        MaterialResponseDTO materialResponseDTO = parseMaterialResponseDTO(party.getMaterial());
        return new PartyEmployeeViewDTO(
                party.getId(),
                party.getLocation(),
                party.getNameClient(),
                party.getDate(),
                party.getValues(),
                materialResponseDTO,
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

    private Party parseParty(PartyRequestDTO partyRequestDTO) {
        Party party = new Party();
        party.setLocation(partyRequestDTO.location());
        party.setNameClient(partyRequestDTO.nameClient());
        party.setDate(partyRequestDTO.date());
        party.setNumberOfPeople(partyRequestDTO.numberOfPeople());
        return party;
    }

    private PartyResponseDTO getPartyResponseDTO(Party party) {
        MaterialResponseDTO materialResponseDTO = parseMaterialResponseDTO(party.getMaterial());
        List<Long> employeeId = party.getPartyEmployees()
                .stream()
                .map(Employee::getId)
                .collect(Collectors.toList());
        List<EmployeePartiesValuesResponseDTO> employeePartiesValuesResponseDTOS = party
                .getValues()
                .stream()
                .map(
                        employeePartiesValues -> new EmployeePartiesValuesResponseDTO(
                                employeePartiesValues.getId(),
                                employeePartiesValues.getEmployeeType(),
                                employeePartiesValues.getValue()
                        )
                )
                .toList();
        return new PartyResponseDTO(
                party.getId(),
                party.getLocation(),
                party.getNameClient(),
                party.getDate(),
                employeePartiesValuesResponseDTOS,
                materialResponseDTO,
                employeeId,
                party.getNumberOfPeople(),
                party.getStatus()
        );
    }

    private Party parseUpdateParty(Long id, PartyUpdateRequestDTO partyRequestDTO) {
        Party party = partyRepository.findById(id).orElseThrow(PartyNotFoundException::new);
        if(!Objects.equals(party.getMaterial().getId(), partyRequestDTO.idMaterial())) {
            Material material = materialRepository.findById(partyRequestDTO.idMaterial()).orElseThrow(MaterialNotFoundException::new);
            party.setMaterial(material);
        }
        List<EmployeePartiesValues> values = partyRequestDTO
                .values()
                .stream()
                .map(valueDTO -> {
                        EmployeeType employeeType = employeeTypeRepository.findById(valueDTO.id()).orElseThrow(EmployeeNotFoundException::new);
                        return new EmployeePartiesValues(
                                employeeType,
                                party,
                                valueDTO.value());
                })
                .collect(Collectors.toList());
        party.getValues().clear();
        party.getValues().addAll(values);
        party.setLocation(partyRequestDTO.location());
        party.setDate(partyRequestDTO.date());
        party.setNameClient(partyRequestDTO.nameClient());
        party.setNumberOfPeople(partyRequestDTO.numberOfPeople());
        party.setStatus(partyRequestDTO.status());
        return party;
    }

    private Page<PartyResponseDTO> parsePartyResponseDTO(Page<Party> parties) {
        if (parties.isEmpty()) {
            return Page.empty();
        }
        return parties.map(this::getPartyResponseDTO);
    }

    private MaterialResponseDTO parseMaterialResponseDTO(Material material) {
        if (material == null) {
            return null;
        }
        return new MaterialResponseDTO(material.getId(), material.getDescription());
    }
}
