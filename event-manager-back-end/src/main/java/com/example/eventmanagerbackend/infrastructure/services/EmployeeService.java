package com.example.eventmanagerbackend.infrastructure.services;

import com.example.eventmanagerbackend.domain.dtos.*;
import com.example.eventmanagerbackend.domain.entities.*;
import com.example.eventmanagerbackend.domain.enums.StatusEmployee;
import com.example.eventmanagerbackend.infrastructure.exceptions.EmployeeNotFoundException;
import com.example.eventmanagerbackend.infrastructure.exceptions.EmployeeTypeNotFoundException;
import com.example.eventmanagerbackend.infrastructure.exceptions.PartyNotFoundException;
import com.example.eventmanagerbackend.infrastructure.mappers.EmployeeMapper;
import com.example.eventmanagerbackend.infrastructure.repositories.EmployeeRepository;
import com.example.eventmanagerbackend.infrastructure.repositories.EmployeeTypeRepository;
import com.example.eventmanagerbackend.infrastructure.repositories.PartyRepository;
import com.example.eventmanagerbackend.infrastructure.utils.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    private final EmployeeTypeRepository employeeTypeRepository;
    private final UserService userService;
    private final PartyRepository partyRepository;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            EmployeeMapper employeeMapper,
            UserService userService,
            EmployeeTypeRepository employeeTypeRepository,
            PartyRepository partyRepository
    ) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.userService = userService;
        this.employeeTypeRepository = employeeTypeRepository;
        this.partyRepository = partyRepository;
    }

    public void createEmployee(EmployeeRequestDTO employee, Authentication authentication) {
        User user = userService.getUser(authentication);
        EmployeeType employeeType = employeeTypeRepository.findById(employee.idEmployeeType()).orElseThrow(EmployeeTypeNotFoundException::new);
        Employee employeeCreated = new Employee(employee, employeeType, user);
        employeeRepository.save(employeeCreated);
    }
    public void updateEmployee(Long id, EmployeeRequestDTO employeeUpdate) {
        Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        updateEmployee(employee, employeeUpdate);
    }

    public Page<EmployeeIdResponseDTO> getAllEmployee(Authentication authentication,Pageable pageable) {
        User user = userService.getUser(authentication);
        return employeeRepository
                .findAllByUser(user,pageable)
                .map(employeeMapper::toEmployeeIdResponseDTO);
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException();
        }
        employeeRepository.deleteById(id);
    }

    public EmployeePartyResponseDTO getEmployeePartyByIdEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        return employeeMapper.toEmployeeResponseDTO(employee);
    }

    public EmployeeIdResponseDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        return employeeMapper.toEmployeeIdResponseDTO(employee);
    }

    public List<EmployeeOptionsResponseDTO> getOptions() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList
                .stream()
                .map(employeeMapper::toEmployeeOptionsResponseDTO)
                .toList();
    }

    public List<StatusResponseDTO> getStatus() {
        return List.of(
                new StatusResponseDTO("ATIVO", "ATIVO"),
                new StatusResponseDTO("INATIVO", "INATIVO")
        );
    }

    public Page<EmployeeResponseDashboardDTO> getEmployeeDashboard(Authentication authentication,Pageable pageable) {
        User user = userService.getUser(authentication);
        Page<Employee> employeesPage = employeeRepository.findAllByUser(user,pageable);

        return employeesPage.map(employee -> {
                    List<Party> parties = employee.getEmployeeParties()
                            .stream()
                            .filter(
                                    party -> DateUtils.initialWeek(party.getDate()))
                            .toList();

            BigDecimal totalSalary = parties
                    .stream()
                    .map(Party::getValues)
                    .flatMap(List::stream)
                    .filter(value -> value.getEmployeeType() == employee.getEmployeeType())
                    .map(EmployeePartiesValues::getValue)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            return new EmployeeResponseDashboardDTO(
                    employee.getId(),
                    employee.getName(),
                    (long) parties.size(),
                    totalSalary,
                    employee.getEmployeeType()
            );
        });
    }
    public Page<EmployeeResponseDashboardDTO> getEmployeeDashboardByDate(Authentication authentication,Pageable pageable, LocalDate fromDate, LocalDate toDate) {
        User user = userService.getUser(authentication);
        Page<Employee> employeePage = employeeRepository.findAllByUser(user,pageable);

        return employeePage.map(employee -> {
            List<Party> parties = employee.getEmployeeParties();

            BigDecimal totalSalary = parties.stream()
                    .filter(party -> {
                        LocalDate date = party.getDate().toLocalDate();
                        return (date.isEqual(fromDate) || date.isAfter(fromDate))
                                && (date.isEqual(toDate) || date.isBefore(toDate));
                    })
                    .map(Party::getValues)
                    .flatMap(List::stream)
                    .filter(value -> value.getEmployeeType() == employee.getEmployeeType())
                    .map(EmployeePartiesValues::getValue)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);


            return new EmployeeResponseDashboardDTO(
                    employee.getId(),
                    employee.getName(),
                    (long) parties.size(),
                    totalSalary,
                    employee.getEmployeeType()
            );
        });
    }


    public Page<EmployeeAddResponseDTO> getEmployeeAddResponseDTO(Authentication authentication,Pageable pageable) {
        User user = userService.getUser(authentication);
        Page<Employee> listEmployee = employeeRepository.findAllByUser(user,pageable);
        List<EmployeeAddResponseDTO> listFilter = listEmployee.getContent()
                .stream()
                .filter(employee -> employee.getStatusEmployee().equals(StatusEmployee.ATIVO))
                .map(employeeMapper::toEmployeeAddResponseDTO)
                .toList();
        return new PageImpl<>(
                listFilter,
                pageable,
                listFilter.size()
        );
    }

    public EmployeePartyDTO getEmployeeParties(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException();
        }
        List<Party> employeeParties = employee.get().getEmployeeParties();
        List<PartyByEmployeeIdDTO> parties = getPartiesByEmployeeIdDTOS(employee.get().getEmployeeType(),employeeParties);

        return new EmployeePartyDTO(
                employee.get().getName(),
                employee.get().getPhone(),
                employee.get().getPixKey(),
                parties
        );
    }

    public List<Long> getEmployeeByPartyId(Long id){
        Party party = partyRepository.findById(id).orElseThrow(PartyNotFoundException::new);
        List<Long> employeeIds = new ArrayList<>();
        for(Employee employee :party.getPartyEmployees()) {
            employeeIds.add(employee.getId());
        }
        return employeeIds;
        
    }


    private static List<PartyByEmployeeIdDTO> getPartiesByEmployeeIdDTOS(EmployeeType employeeType, List<Party> employeeParties) {
        List<PartyByEmployeeIdDTO> parties = new ArrayList<>();
        for (Party party : employeeParties) {
            BigDecimal employeeValue = party.getValues().stream()
                    .filter(value -> value.getEmployeeType() == employeeType)
                    .map(EmployeePartiesValues::getValue)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            parties.add(
                    new PartyByEmployeeIdDTO(
                            party.getLocation(),
                            party.getNameClient(),
                            party.getDate(),
                            employeeValue,
                            party.getStatus(),
                            party.getNumberOfPeople()
                    )
            );
        }
        return parties;
    }

    private void updateEmployee(Employee employee, EmployeeRequestDTO employeeUpdate) {
        employee.setName(employeeUpdate.name());
        employee.setPhone(employeeUpdate.phone());
        employee.setStatusEmployee(employeeUpdate.statusEmployee());
        employee.setPixKey(employeeUpdate.pixKey());
        EmployeeType employeeType = employeeTypeRepository.findById(employeeUpdate.idEmployeeType()).orElseThrow(EmployeeNotFoundException::new);
        employee.setEmployeeType(employeeType);
        employeeRepository.save(employee);
    }
}
