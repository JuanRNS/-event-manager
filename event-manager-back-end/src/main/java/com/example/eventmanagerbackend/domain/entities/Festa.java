package com.example.eventmanagerbackend.domain.entities;

import com.example.eventmanagerbackend.domain.enums.StatusFesta;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "festas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Festa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String location;
    
    @Column(name = "name_client", nullable = false)
    private String nameClient;
    
    @Column(nullable = false)
    private LocalDateTime date;
    
    @Column(name = "value_per_day", nullable = false, precision = 10, scale = 2)
    private BigDecimal valuePerDay;
    @Column(name = "status")
    private StatusFesta status = StatusFesta.AGENDADA;

    @OneToMany(mappedBy = "festa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FestaGarcom> festaGarcoms = new ArrayList<>();

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "material_id")
    private Material material;

}
