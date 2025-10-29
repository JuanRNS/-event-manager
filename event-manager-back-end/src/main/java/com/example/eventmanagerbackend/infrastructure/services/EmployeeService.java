package com.example.eventmanagerbackend.infrastructure.services;

import com.example.eventmanagerbackend.domain.dtos.*;
import com.example.eventmanagerbackend.domain.entities.*;
import com.example.eventmanagerbackend.infrastructure.exceptions.EmployeeNotFoundException;
import com.example.eventmanagerbackend.infrastructure.mappers.EmployeeMapper;
import com.example.eventmanagerbackend.infrastructure.repositories.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
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
public class EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    private final UserService userService;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, UserService userService) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.userService = userService;
    }

    public Employee createEmployee(EmployeeRequestDTO employee, Authentication authentication) {
        User user = userService.getUser(authentication);
        Employee employeeCreated = new Employee(employee, user);
        return employeeRepository.save(employeeCreated);
    }
    public void updateEmployee(Long id, EmployeeRequestDTO employeeUpdate) {
        Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        updateEmployee(employee, employeeUpdate);
    }

    public Page<EmployeeResponseDTO > getAllEmployee(Authentication authentication,Pageable pageable) {
        User user = userService.getUser(authentication);
        return employeeRepository
                .findAllByUser(user,pageable)
                .map(employeeMapper::toGarcomResponseDTO);
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException();
        }
        employeeRepository.deleteById(id);
    }

    public EmployeeResponseDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        return employeeMapper.toGarcomResponseDTO(employee);
    }

    public List<EmployeeOptionsResponseDTO> getOptions() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList
                .stream()
                .map(employeeMapper::toGarcomOptionsResponseDTO)
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
                    List<Party> parties = employee.getParties()
                            .stream()
                            .filter(
                                    party -> initialWeek(party.getDate()))
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
            List<Party> parties = employee.getParties();

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


    public Page<EmployeeAddResponseDTO> getGarcomAddResponseDTO(Pageable pageable) {
        return employeeRepository.findAll(pageable)
                .map(employeeMapper::toGarcomAddResponseDTO);
    }

    public EmployeePartyDTO getEmployeeFestas(Long id, Pageable pageable) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException();
        }
        List<Party> employeeParties = employee.get().getParties();
        List<PartyByEmployeeIdDTO> festas = getFestasByGarcomIdDTOS(employee.get().getEmployeeType(),employeeParties);

        return new EmployeePartyDTO(
                employee.get().getName(),
                employee.get().getPhone(),
                employee.get().getPixKey(),
                festas
        );
    }


    private static List<PartyByEmployeeIdDTO> getFestasByGarcomIdDTOS(EmployeeType employeeType, List<Party> employeeParties) {
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
        employee.setStatusGarcom(employeeUpdate.statusGarcom());
        employee.setPixKey(employeeUpdate.pixKey());
        employeeRepository.save(employee);
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
