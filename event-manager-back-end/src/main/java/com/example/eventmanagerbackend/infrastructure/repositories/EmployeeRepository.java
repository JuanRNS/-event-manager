package com.example.eventmanagerbackend.infrastructure.repositories;

import com.example.eventmanagerbackend.domain.entities.Employee;
import com.example.eventmanagerbackend.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findAllByUser(User user, Pageable pageable);
}
