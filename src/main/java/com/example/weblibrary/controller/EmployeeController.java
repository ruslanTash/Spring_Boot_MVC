package com.example.weblibrary.controller;

import DTO.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.weblibrary.model.Employee;
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
    public EmployeeDTO getEmployeeById(@PathVariable int id){
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
    public List<EmployeeDTO> findEmployeeBySalaryGreaterThan(@RequestParam("salary") int salary){
        return employeeService.findEmployeeBySalaryGreaterThan(salary);
    }

}