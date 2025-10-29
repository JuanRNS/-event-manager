package com.example.eventmanagerbackend.infrastructure.repositories;

import com.example.eventmanagerbackend.domain.entities.EmployeeType;
import com.example.eventmanagerbackend.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Long> {

    List<EmployeeType> findByUser(User user);
}
