package com.example.weblibrary.service;

import DTO.EmployeeDTO;
import com.example.weblibrary.model.Employee;

import java.util.List;

public interface EmployeeService {



    List<EmployeeDTO> getAllEmployees();

    void setSalaryById(int id, int newSalary);


    EmployeeDTO getEmplpyeeById(int id);

    void deleteEmployeeById(int id);

    List<EmployeeDTO> findEmployeeBySalaryGreaterThan(int salary);
}
