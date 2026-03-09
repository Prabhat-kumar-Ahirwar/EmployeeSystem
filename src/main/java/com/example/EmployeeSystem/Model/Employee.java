package com.example.EmployeeSystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is compulsory")
    private String name;

    @NotNull(message = "Salary is required")
    private Float salary;

    @NotBlank(message = "Position can't be blank")
    private String position;

    @NotNull(message = "Joining date can't be null")
    private LocalDate joiningDate;
}