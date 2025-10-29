package com.example.eventmanagerbackend.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EmployeeType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
