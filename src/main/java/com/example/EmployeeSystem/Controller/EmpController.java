package com.example.EmployeeSystem.Controller;

import com.example.EmployeeSystem.Exception.EmployeeNotFoundException;
import com.example.EmployeeSystem.Model.Employee;
import com.example.EmployeeSystem.Repository.EmpRepo;
import com.example.EmployeeSystem.Service.EmpService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.hibernate.sql.model.EntityMutationOperationGroup;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class EmpController {

    private final EmpService empService;
    private final EmpRepo empRepo;

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmp(@Valid @RequestBody Employee employee){
        Employee savedEmployee = empService.addEmp(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getEmp(){
        List<Employee> employees = empService.getAllEmp();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Employee getEmpById(@PathVariable Long id){
        return empRepo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeEmp(@PathVariable Long id){
        empService.remove(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmp(@PathVariable Long id,
                                              @RequestBody Employee employee){

        Employee updatedEmployee = empService.updateEmp(id, employee);

        return ResponseEntity.ok(updatedEmployee);
    }

    @GetMapping("search/position/{position}")
    public ResponseEntity<List<Employee>> getEmployeeByPosition(@PathVariable String position){
        List<Employee> employees = empService.getByPosition(position);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<List<Employee>> getByName(@PathVariable String name){
        List<Employee> employees = empService.getByName(name);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}