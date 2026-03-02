package com.example.EmployeeSystem.Controller;

import com.example.EmployeeSystem.Model.Employee;
import com.example.EmployeeSystem.Service.EmpService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class EmpController {

    private final EmpService empService;

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmp(@RequestBody Employee employee){
        Employee savedEmployee = empService.addEmp(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getEmp(){
        List<Employee> employees = empService.getAllEmp();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> empById(@PathVariable Long id){
        Employee employee = empService.getEmpById(id);
        if(employee == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}