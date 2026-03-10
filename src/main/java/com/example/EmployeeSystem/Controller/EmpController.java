package com.example.EmployeeSystem.Controller;

import com.example.EmployeeSystem.Exception.EmployeeNotFoundException;
import com.example.EmployeeSystem.Model.Employee;
import com.example.EmployeeSystem.Repository.EmpRepo;
import com.example.EmployeeSystem.Service.EmpService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.hibernate.sql.model.EntityMutationOperationGroup;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<Page<Employee>> getEmp(
            @RequestParam int size,
            @RequestParam int page
    ){
        Page<Employee> employees = empService.getAllEmp(page,size);
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
    public ResponseEntity<Page<Employee>> getEmployeeByPosition(@PathVariable String position,
                                                                @RequestParam int page, @RequestParam int size){
        Page<Employee> employees = empService.getByPosition(position,page,size);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<Page<Employee>> getByName(@PathVariable String name , @RequestParam int page , int size){
        Page<Employee> employees = empService.getByName(name,page,size);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @GetMapping("/search/joindate/{joiningdate}")
    public ResponseEntity<List<Employee>> findByJoiningDateAfter(
            @PathVariable("joiningdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate joiningDate) {

        List<Employee> employees = empService.findByJoiningDateAfter(joiningDate);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @GetMapping("/search/{min}/{max}")
    public ResponseEntity<List<Employee>> getEmployeeBySalaryRange(@PathVariable float min ,@PathVariable float max){
        List<Employee> employees = empService.getEmpBySalaryRange(min,max);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}