package com.example.eventmanagerbackend.infrastructure.repositories;

import com.example.eventmanagerbackend.domain.entities.Party;
import com.example.eventmanagerbackend.domain.enums.StatusFesta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {

    List<Party> findAllByStatus(StatusFesta statusFesta);

    Page<Party> findAllByStatus(StatusFesta statusFesta, Pageable pageable);
}
