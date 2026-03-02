package com.example.EmployeeSystem.Repository;

import com.example.EmployeeSystem.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EmpRepo extends JpaRepository<Employee ,Long> {
}
