package com.example.eventmanagerbackend.domain.festagarcom;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class FestaGarcomId implements Serializable {

    private Long festaId;

    private Long garcomId;
}
