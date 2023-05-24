package com.example.weblibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.weblibrary.pojo.Employee;
import com.example.weblibrary.service.EmployeeService;

import java.util.List;
import java.util.OptionalInt;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping()
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }



//    Получение суммы зарплат сотрудников
    @GetMapping("salary/sum")
    public int getSumSalary(){
        return employeeService.getSumSalary();
    }

//    Получение суммы зарплат сотрудников
    @GetMapping("salary/min")
    public int getMinSalary(){
        return employeeService.getMinSalary();
    }

//    Получение сотрудника с максимальной зарплатой
    @GetMapping("salary/max")
    public int getMaxSalary() {
        return employeeService.getMaxSalary();
    }

//    Получение всех сотрудников, зарплата которых больше средней
    @GetMapping("high-salary")
    public List<Employee>  getHighSalary(){
        return employeeService.getHighSalary();
    }
}
