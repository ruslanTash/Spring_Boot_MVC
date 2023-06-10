package com.example.weblibrary.service;

import DTO.EmployeeDTO;
import DTO.EmployeeFullDTO;

import java.util.List;

public interface EmployeeService {



    List<EmployeeDTO> getAllEmployees();

    List<EmployeeFullDTO> getAllFullEmployees();

    void setSalaryById(int id, int newSalary);


    EmployeeDTO getEmplpyeeById(int id);

    void deleteEmployeeById(int id);

    List<EmployeeDTO> findEmployeeBySalaryGreaterThan(int salary);

    List<EmployeeDTO> getEmployeeWithHighestSalary();

    List<EmployeeFullDTO> getEmplpoyeeByPosition(Integer positionId);

    EmployeeFullDTO getEmployeeFullInfoById(Integer id);

    List<EmployeeDTO> getEmployeeByPage(int page);
}
