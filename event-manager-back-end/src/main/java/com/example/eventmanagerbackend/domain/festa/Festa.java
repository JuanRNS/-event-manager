package com.example.eventmanagerbackend.domain.festa;

import com.example.eventmanagerbackend.domain.festagarcom.FestaGarcom;
import com.example.eventmanagerbackend.domain.garcom.Garcom;
import com.example.eventmanagerbackend.domain.material.Material;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String local;
    
    @Column(name = "nome_cliente", nullable = false)
    private String nomeCliente;
    
    @Column(nullable = false)
    private LocalDateTime data;
    
    @Column(name = "valor_diaria_garcom", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorDiariaGarcom;

    @OneToMany(mappedBy = "festa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FestaGarcom> festaGarcoms = new ArrayList<>();

    @ManyToOne
    private Material materiais = new Material();

}
