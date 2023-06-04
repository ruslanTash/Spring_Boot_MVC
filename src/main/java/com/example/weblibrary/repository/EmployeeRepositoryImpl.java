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
            new Employee(1,"Катя", 90_000),
            new Employee(2, "Дима", 102_000),
            new Employee(3, "Олег", 80_000),
            new Employee(4, "Вика", 165_000));

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>(employeeList);
        return employees;
    }

    public void removeEmployee(Employee employee){
        getAllEmployees().remove(employee);
    }
}