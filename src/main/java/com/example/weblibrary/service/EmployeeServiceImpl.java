package com.example.weblibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.weblibrary.pojo.Employee;
import com.example.weblibrary.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collectors;

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
    public Employee getEmployeeWithMinSalary() {
        return employeeRepository.getAllEmployees().stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(new Employee());
    }

    @Override
    public Employee getEmployeeWithMaxSalary() {
        return employeeRepository.getAllEmployees().stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(new Employee());
    }

    @Override
    public List<Employee> getHighSalary() {
        List<Employee> highSalaryList = employeeRepository.getAllEmployees().stream()
                .filter(employee -> employee.getSalary() > getAverageSalary())
                .toList();
        return highSalaryList;
    }

    @Override
    public List<Employee> newEmployeeList() {
        return employeeRepository.getAllEmployees();
    }

    @Override
    public void setSalaryById(int id, int newSalary) {
        employeeRepository.getAllEmployees().stream()
                .filter(e->e.getId() == id)
                .forEach(e->e.setSalary(newSalary));
    }

    @Override
    public Employee getEmplpyeeById(int id) {
         return employeeRepository.getAllEmployees().stream()
                 .filter(e-> e.getId() == id)
                 .findAny()
                 .orElse(null);
            }

    @Override
    public void deleteEmployeeById(int id) {
        employeeRepository.getAllEmployees().remove(getEmplpyeeById(id));
    }

    @Override
    public List<Employee> salaryHigherThan(int salary) {
        return employeeRepository.getAllEmployees().stream()
                .filter(e-> e.getSalary() > salary)
                .collect(Collectors.toList());
    }


    public int getAverageSalary() {
        OptionalDouble averageSalary = employeeRepository.getAllEmployees().stream()
                .mapToInt(a -> a.getSalary())
                .average();
        int averageInt = (int) averageSalary.getAsDouble();
        return averageInt;
    }

}