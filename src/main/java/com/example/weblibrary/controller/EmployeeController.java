package com.example.weblibrary.controller;

import DTO.EmployeeDTO;
import DTO.EmployeeFullDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.weblibrary.service.EmployeeService;

import java.util.List;


@RestController
@RequestMapping("employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    //        POST-запрос: localhost:8080/employees - создаёт множество новых сотрудников;
    @PostMapping("/")
    public List<EmployeeDTO> newEmployeeList() {
        return employeeService.getAllEmployees();
    }

    //        PUT-запрос: localhost:8080/employees/{id} - редактирует сотрудника с указанным id;
    @PutMapping("/{id}/{newSalary}")
    public void setSalaryById(@PathVariable int id,
                              @PathVariable int newSalary) {
        employeeService.setSalaryById(id, newSalary);
    }

    //        GET-запрос: localhost:8080/employees/{id} - возвращает информацию о сотруднике с переданным id;
    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable int id) {
        return employeeService.getEmplpyeeById(id);
    }


    //        DELETE-запрос: localhost:8080/employees/{id} - удаляет сотрудника с переданным id;
    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable int id) {
        employeeService.deleteEmployeeById(id);
    }

    //        GET-запрос: localhost:8080/employees/salaryHigherThan?salary=
//        - возвращает всех сотрудников, зарплата которых выше переданного параметра salary.
    @GetMapping("/salaryHigherThan")
    public List<EmployeeDTO> findEmployeeBySalaryGreaterThan(@RequestParam("salary") int salary) {
        return employeeService.findEmployeeBySalaryGreaterThan(salary);
    }


    //    Реализуйте следующие REST-методы в приложении для учета сотрудников:
//
//    GET-запрос: localhost:8080/employees/withHighestSalary - возвращает информацию о сотрудниках с самой высокой зарплатой в фирме;
    @GetMapping("/withHighestSalary")
    public List<EmployeeDTO> getEmployeeWithHighestSalary() {
        return employeeService.getEmployeeWithHighestSalary();
    }

    //    GET-запрос: localhost:8080/employees?position= - принимаут на вход опциональный параметр position
//    и возвращать информацию о всех сотрудниках фирмы, указанной в параметре должности. Если параметр не указан, то возвращать необходимо всех сотрудников.
    @GetMapping("")
    public List<EmployeeFullDTO> getEmployeeByPosition(@RequestParam("position") Integer positionId) {
        return employeeService.getEmplpoyeeByPosition(positionId);
    }

    //    GET-запрос: localhost:8080/employees/{id}/fullInfo
//. Он должен возвращать полную информацию о сотруднике (имя, зарплата, название должности) с переданным в пути запроса идентификатором.
    @GetMapping("/{id}/fullInfo")
    public EmployeeFullDTO getEmployeeFullInfoById(@PathVariable(name = "id") Integer id) {
        return employeeService.getEmployeeFullInfoById(id);
    }

    //    GET-запрос: localhost:8080/employees/page?page=
//. Он должен возвращать информацию о сотрудниках, основываясь на номере страницы.

    @GetMapping("/page")
    public List<EmployeeDTO> getEmployeeByPage(@RequestParam("page") int page) {
        return employeeService.getEmployeeByPage(page);
    }

}