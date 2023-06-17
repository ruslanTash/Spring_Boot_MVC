package com.example.weblibrary.controller;

import com.example.weblibrary.DTO.EmployeeDTO;
import com.example.weblibrary.DTO.EmployeeFullDTO;
import com.example.weblibrary.model.Report;
import com.example.weblibrary.service.ReportService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.example.weblibrary.service.EmployeeService;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/")
    public List<EmployeeDTO> newEmployeeList() {
        return employeeService.getAllEmployees();
    }

    @PutMapping("/{id}/{newSalary}")
    public void setSalaryById(@PathVariable int id,
                              @PathVariable int newSalary) {
        employeeService.setSalaryById(id, newSalary);
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable int id) {
        return employeeService.getEmplpyeeById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable int id) {
        employeeService.deleteEmployeeById(id);
    }

    @GetMapping("/salaryHigherThan")
    public List<EmployeeDTO> findEmployeeBySalaryGreaterThan(@RequestParam("salary") int salary) {
        return employeeService.findEmployeeBySalaryGreaterThan(salary);
    }

    @GetMapping("/withHighestSalary")
    public List<EmployeeDTO> getEmployeeWithHighestSalary() {
        return employeeService.getEmployeeWithHighestSalary();
    }

    @GetMapping("")
    public List<EmployeeFullDTO> getEmployeeByPosition(@RequestParam("position") Integer positionId) {
        return employeeService.getEmplpoyeeByPosition(positionId);
    }

    @GetMapping("/{id}/fullInfo")
    public EmployeeFullDTO getEmployeeFullInfoById(@PathVariable(name = "id") Integer id) {
        return employeeService.getEmployeeFullInfoById(id);
    }

    @GetMapping("/page")
    public List<EmployeeDTO> getEmployeeByPage(@RequestParam("page") int page) {
        return employeeService.getEmployeeByPage(page);
    }


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadEmployees(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        employeeService.uploadEmployees(multipartFile);
    }
}