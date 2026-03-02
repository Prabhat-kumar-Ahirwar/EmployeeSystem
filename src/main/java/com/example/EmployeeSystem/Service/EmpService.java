package com.example.EmployeeSystem.Service;

import com.example.EmployeeSystem.Model.Employee;
import com.example.EmployeeSystem.Repository.EmpRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpService {

    private final EmpRepo empRepo;

    public Employee addEmp(Employee employee){
        return empRepo.save(employee);
    }
    public List<Employee> getAllEmp(){
        return empRepo.findAll();
    }
    public Employee getEmpById(Long id){
        return empRepo.findById(id).orElse(null);
    }
    public void remove(Long id){
        Employee employee = empRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        empRepo.delete(employee);
    }
}