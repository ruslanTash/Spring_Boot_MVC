package com.example.weblibrary.service;

import com.example.weblibrary.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    int getSumSalary();

    Employee getEmployeeWithMinSalary();

    Employee getEmployeeWithMaxSalary();

    List<Employee> getHighSalary();

    List<Employee> newEmployeeList();

    void setSalaryById(int id, int newSalary);

    Employee getEmplpyeeById(int id);

    void deleteEmployeeById(int id);

    List<Employee> salaryHigherThan(int salary);
}
