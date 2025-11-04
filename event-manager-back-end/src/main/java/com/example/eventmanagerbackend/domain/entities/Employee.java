package com.example.eventmanagerbackend.domain.entities;

import com.example.eventmanagerbackend.domain.dtos.EmployeeRequestDTO;
import com.example.eventmanagerbackend.domain.enums.StatusGarcom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(name = "pix_key", nullable = false, unique = true)
    private String pixKey;
    
    @Column(nullable = false)
    private String phone;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "employee_type_id")
    private EmployeeType employeeType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusGarcom statusGarcom = StatusGarcom.ATIVO;

    @ManyToMany(mappedBy = "partyEmployees", fetch = FetchType.LAZY)
    private List<Party> parties = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Employee(EmployeeRequestDTO employeeRequestDTO,EmployeeType employeeType, User user){
        this.name = employeeRequestDTO.name();
        this.pixKey = employeeRequestDTO.pixKey();
        this.phone = employeeRequestDTO.phone();
        this.employeeType = employeeType;
        this.statusGarcom = employeeRequestDTO.statusGarcom();
        this.user = user;
    }
}
