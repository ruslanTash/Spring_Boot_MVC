package com.example.weblibrary.controller;

import com.example.weblibrary.DTO.EmployeeDTO;
import com.example.weblibrary.DTO.EmployeeFullDTO;
import com.example.weblibrary.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin/employees")
@RequiredArgsConstructor
public class AdminEmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/all")
    public List<EmployeeDTO> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @PutMapping("/{id}/{newSalary}")
    public void setSalaryById(@PathVariable int id,
                              @PathVariable int newSalary) {
        employeeService.setSalaryById(id, newSalary);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable int id) {
        employeeService.deleteEmployeeById(id);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadEmployees(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        employeeService.uploadEmployees(multipartFile);
    }

}
