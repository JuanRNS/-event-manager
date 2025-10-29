package com.example.eventmanagerbackend.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class EmployeePartiesValues {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_type_id", nullable = false)
    private EmployeeType employeeType;

    @ManyToOne
    @JoinColumn(name = "party_id")
    private Party party;

    @Column(nullable = false)
    private BigDecimal value;

}
