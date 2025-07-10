package com.example.eventmanagerbackend.domain.garcom;

import com.example.eventmanagerbackend.domain.festa.Festa;
import com.example.eventmanagerbackend.domain.festagarcom.FestaGarcom;
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
    private String nome;
    
    @Column(name = "chave_pix", nullable = false, unique = true)
    private String chavePix;
    
    @Column(nullable = false)
    private String telefone;
    
    @Column(nullable = false)
    private boolean ativo = true;

    @OneToMany(mappedBy = "garcom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FestaGarcom> festaGarcoms = new ArrayList<>();
    
    public Garcom(String nome, String chavePix, String telefone) {
        this.nome = nome;
        this.chavePix = chavePix;
        this.telefone = telefone;
    }
}
