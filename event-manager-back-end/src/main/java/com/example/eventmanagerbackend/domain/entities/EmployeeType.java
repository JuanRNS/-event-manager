package com.example.eventmanagerbackend.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class employeeType {

    @Id
    private Long id;

    private String type;
}
