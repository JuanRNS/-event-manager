package com.example.eventmanagerbackend.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "festa_garcom")
@Data
public class FestaGarcom {


    @EmbeddedId
    private FestaGarcomId id;

    @ManyToOne
    @MapsId("festaId")
    @JoinColumn(name = "festa_id")
    @JsonIgnore
    private Festa festa;

    @ManyToOne
    @MapsId("garcomId")
    @JoinColumn(name = "garcom_id")
    @JsonIgnore
    private Garcom garcom;

    @Column(name = "valor_diaria_garcom", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorDiariaGarcom;


}
