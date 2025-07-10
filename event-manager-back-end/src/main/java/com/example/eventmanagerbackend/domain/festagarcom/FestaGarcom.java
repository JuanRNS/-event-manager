package com.example.eventmanagerbackend.domain.festagarcom;

import com.example.eventmanagerbackend.domain.festa.Festa;
import com.example.eventmanagerbackend.domain.garcom.Garcom;
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
    private Festa festa;

    @ManyToOne
    @MapsId("garcomId")
    @JoinColumn(name = "garcom_id")
    private Garcom garcom;

    @Column(name = "valor_diaria_garcom", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorDiariaGarcom;


}
