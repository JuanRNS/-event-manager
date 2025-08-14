package com.example.eventmanagerbackend.domain.entities;

import com.example.eventmanagerbackend.domain.dtos.GarcomRequestDTO;
import com.example.eventmanagerbackend.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "garcons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Garcom {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(name = "pix_key", nullable = false, unique = true)
    private String pixKey;
    
    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ATIVO;

    @OneToMany(mappedBy = "garcom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FestaGarcom> festaGarcoms = new ArrayList<>();
}
