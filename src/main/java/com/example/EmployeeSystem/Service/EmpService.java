package com.example.EmployeeSystem.Service;

import com.example.EmployeeSystem.Exception.SalaryNotNegativeException;
import com.example.EmployeeSystem.Model.Employee;
import com.example.EmployeeSystem.Repository.EmpRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpService {

    private final EmpRepo empRepo;

    public Employee addEmp(Employee employee){
        if(employee.getSalary()<0){
            throw new SalaryNotNegativeException("Salary can not be negative or 0");
        }
        return empRepo.save(employee);
    }
    public Page<Employee> getAllEmp(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return empRepo.findAll(pageable);
    }
    public Employee getEmpById(Long id){
        return empRepo.findById(id).orElse(null);
    }
    public void remove(Long id){
        Employee employee = empRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        empRepo.delete(employee);
    }
    public Employee updateEmp(Long id, Employee employee){
        Employee existingEmployee = empRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        existingEmployee.setName(employee.getName());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setPosition(employee.getPosition());
        existingEmployee.setJoiningDate(employee.getJoiningDate());
        return empRepo.save(existingEmployee);
    }

    public List<Employee> getByPosition(String position) {
        return empRepo.findByPositionContaining(position);
    }

    public Page<Employee> getByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return empRepo.findByNameContaining(name, pageable);
    }

    public List<Employee> findByJoiningDateAfter(LocalDate joiningDate) {
        return empRepo.findByJoiningDateAfter(joiningDate);
    }

    public List<Employee> getEmpBySalaryRange(float min, float max) {
        return empRepo.findEmployeesBySalaryRange(min,max);
    }
}