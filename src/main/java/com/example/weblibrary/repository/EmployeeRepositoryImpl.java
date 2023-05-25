package com.example.weblibrary.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.weblibrary.pojo.Employee;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    // Коллекция для имитации данных
    private final List<Employee> employeeList = List.of(
            new Employee("Катя", 90_000),
            new Employee("Дима", 102_000),
            new Employee("Олег", 80_000),
            new Employee("Вика", 165_000));

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>(employeeList);
        return employees;
    }
}