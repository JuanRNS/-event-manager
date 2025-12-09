package com.example.eventmanagerbackend.infrastructure.repositories;

import com.example.eventmanagerbackend.domain.entities.Material;
import com.example.eventmanagerbackend.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    List<Material> findAllByUser(User user);

    Material findByIdAndUser(Long id, User user);
}
