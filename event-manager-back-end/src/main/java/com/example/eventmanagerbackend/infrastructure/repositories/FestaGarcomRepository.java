package com.example.eventmanagerbackend.infrastructure.repositories;

import com.example.eventmanagerbackend.domain.entities.Festa;
import com.example.eventmanagerbackend.domain.entities.FestaGarcom;
import com.example.eventmanagerbackend.domain.entities.FestaGarcomId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FestaGarcomRepository extends JpaRepository<FestaGarcom, FestaGarcomId> {

    @Query("SELECT fg.festa FROM FestaGarcom fg WHERE fg.garcom.id = :garcomId")
    List<Festa> findFestasByGarcomId(@Param("garcomId") Long garcomId);
}
