package com.example.weblibrary.service;

import com.example.weblibrary.model.Employee;

import java.util.List;

public interface EmployeeService {



    List<Employee> getAllEmployees();

    void setSalaryById(int id, int newSalary);


    Employee getEmplpyeeById(int id);

    void deleteEmployeeById(int id);

    List<Employee> findEmployeeBySalaryGreaterThan(int salary);
}
