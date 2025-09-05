package com.example.eventmanagerbackend.infrastructure.repositories;

import com.example.eventmanagerbackend.domain.entities.Festa;
import com.example.eventmanagerbackend.domain.enums.StatusFesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FestaRepository extends JpaRepository<Festa, Long> {

    List<Festa> findAllByStatus(StatusFesta statusFesta);
}
