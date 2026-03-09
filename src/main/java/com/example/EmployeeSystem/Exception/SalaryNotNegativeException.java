package com.example.EmployeeSystem.Exception;

public class SalaryNotNegativeException extends RuntimeException{
    public SalaryNotNegativeException(String message){
        super(message);
    }
}
