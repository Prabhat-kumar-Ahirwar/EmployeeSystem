package com.example.EmployeeSystem.Service;

import com.example.EmployeeSystem.Model.Employee;
import com.example.EmployeeSystem.Repository.EmpRepo;
import lombok.RequiredArgsConstructor;
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
}