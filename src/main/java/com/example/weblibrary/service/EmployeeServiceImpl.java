package com.example.weblibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.weblibrary.pojo.Employee;
import com.example.weblibrary.repository.EmployeeRepository;

import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @Override
    public int getSumSalary() {
        int sum = employeeRepository.getAllEmployees().stream()
                .mapToInt(a -> a.getSalary())
                .sum();
        return sum;
    }

    @Override
    public int getMinSalary() {
        OptionalInt min = employeeRepository.getAllEmployees().stream()
                .mapToInt(a -> a.getSalary())
                .min();
        return min.getAsInt();
    }

    @Override
    public int getMaxSalary() {
        OptionalInt max = employeeRepository.getAllEmployees().stream()
                .mapToInt(a -> a.getSalary())
                .max();
        return max.getAsInt();
    }

    @Override
    public List<Employee> getHighSalary() {
        List<Employee> highSalaryList = employeeRepository.getAllEmployees().stream()
                .filter(employee -> employee.getSalary() > getAverageSalary())
                .toList();
        return highSalaryList;
    }

    public int getAverageSalary() {
        OptionalDouble averageSalary = employeeRepository.getAllEmployees().stream()
                .mapToInt(a -> a.getSalary())
                .average();
        int averageInt = (int) averageSalary.getAsDouble();
        return averageInt;
    }
}