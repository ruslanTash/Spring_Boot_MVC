package com.example.weblibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.weblibrary.pojo.Employee;
import com.example.weblibrary.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping()
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }


    //    Получение суммы зарплат сотрудников
    @GetMapping("/salary/sum")
    public int getSumSalary() {
        return employeeService.getSumSalary();
    }

    //    Получение сотрудника с минимальной зарплатой
    @GetMapping("/salary/min")
    public Employee getEmployeeWithMinSalary() {
        return employeeService.getEmployeeWithMinSalary();
    }

    //    Получение сотрудника с максимальной зарплатой
    @GetMapping("/salary/max")

    public Employee getEmployeeWithMaxSalary() {
        return employeeService.getEmployeeWithMaxSalary();
    }

    //    Получение всех сотрудников, зарплата которых больше средней
    @GetMapping("/high-salary")
    public List<Employee> getHighSalary() {
        return employeeService.getHighSalary();
    }



//        POST-запрос: localhost:8080/employees - создаёт множество новых сотрудников;
    @PostMapping("/")
    public List<Employee> newEmployeeList() {
        return employeeService.newEmployeeList();
    }

//        PUT-запрос: localhost:8080/employees/{id} - редактирует сотрудника с указанным id;
    @PutMapping("/{id}/{newSalary}")
    public void setSalaryById(@PathVariable int id,
                              @PathVariable int newSalary) {
        employeeService.setSalaryById(id, newSalary);
    }
//        GET-запрос: localhost:8080/employees/{id} - возвращает информацию о сотруднике с переданным id;
    @GetMapping("/{id}")
    public Employee getEmplpyeeById (@PathVariable int id){
        return employeeService.getEmplpyeeById(id);
    }


//        DELETE-запрос: localhost:8080/employees/{id} - удаляет сотрудника с переданным id;
    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable int id){
        employeeService.deleteEmployeeById(id);
    }

//        GET-запрос: localhost:8080/employees/salaryHigherThan?salary=
//        - возвращает всех сотрудников, зарплата которых выше переданного параметра salary.
    @GetMapping("/salaryHigherThan")
    public List<Employee> salaryHigherThan(@RequestParam("salary") int salary){
        return employeeService.salaryHigherThan(salary);
    }

}