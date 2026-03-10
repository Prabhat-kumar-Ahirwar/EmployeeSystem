package com.example.EmployeeSystem.Repository;

import com.example.EmployeeSystem.Model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmpRepo extends JpaRepository<Employee ,Long> {
    List<Employee> findByPositionContaining(String position);


    List<Employee> findByJoiningDateAfter(LocalDate joiningDate);

    @Query("SELECT e FROM Employee e WHERE e.salary BETWEEN :minSalary AND :maxSalary")
    List<Employee> findEmployeesBySalaryRange(@Param("minSalary") Float minSalary,
                                              @Param("maxSalary") Float maxSalary);

    Page<Employee> findByNameContaining(String name, Pageable pageable);
}
