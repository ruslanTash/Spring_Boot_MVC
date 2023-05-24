package com.example.weblibrary.service;

import com.example.weblibrary.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    int getSumSalary();

    int getMinSalary();

    int getMaxSalary();

    List<Employee> getHighSalary();
}
