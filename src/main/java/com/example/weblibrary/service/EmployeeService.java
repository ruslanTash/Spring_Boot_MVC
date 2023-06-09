package com.example.weblibrary.service;

import DTO.EmployeeDTO;

import java.util.List;

public interface EmployeeService {



    List<EmployeeDTO> getAllEmployees();

    void setSalaryById(int id, int newSalary);


    EmployeeDTO getEmplpyeeById(int id);

    void deleteEmployeeById(int id);

    List<EmployeeDTO> findEmployeeBySalaryGreaterThan(int salary);

    List<EmployeeDTO> getEmployeeWithHighestSalary();

    List<EmployeeDTO> getEmplpoyeeByPosition(Integer positionId);
}
