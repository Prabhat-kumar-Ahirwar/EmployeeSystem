package com.example.EmployeeSystem.Repository;

import com.example.EmployeeSystem.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmpRepo extends JpaRepository<Employee ,Long> {
    List<Employee> findByPositionContaining(String position);

    List<Employee> findByName(String name);

    List<Employee> findByJoiningDateAfter(LocalDate joiningDate);
}
