package com.example.eventmanagerbackend.infrastructure.repositories;

import com.example.eventmanagerbackend.domain.entities.Employee;
import com.example.eventmanagerbackend.domain.entities.User;
import com.example.eventmanagerbackend.domain.enums.StatusEmployee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findAllByUser(User user, Pageable pageable);


    Page<Employee> findAllByStatusEmployeeAndUser(StatusEmployee statusEmployee, User user, Pageable pageable);

    Long countByStatusEmployeeAndUser(StatusEmployee statusEmployee, User user);
    @Query("SELECT e FROM Employee e WHERE e.id IN :ids AND e.user = :user")
    List<Employee> findAllByIdAndUser(User user,List<Long> ids);

    Employee findByIdAndUser(Long id, User user);
}
