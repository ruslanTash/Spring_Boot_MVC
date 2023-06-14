package com.example.weblibrary.service;

import com.example.weblibrary.DTO.EmployeeDTO;
import com.example.weblibrary.DTO.EmployeeFullDTO;
import com.example.weblibrary.model.Report;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {



    List<EmployeeDTO> getAllEmployees();

    List<EmployeeFullDTO> getAllFullEmployees();

    void setSalaryById(int id, int newSalary);

    EmployeeDTO getEmplpyeeById(int id);

    void deleteEmployeeById(int id);

    List<EmployeeDTO> findEmployeeBySalaryGreaterThan(int salary);

    List<EmployeeDTO> getEmployeeWithHighestSalary();

    List<EmployeeFullDTO> getEmplpoyeeByPosition(Integer positionId);

    EmployeeFullDTO getEmployeeFullInfoById(Integer id);

    List<EmployeeDTO> getEmployeeByPage(int page);

    void uploadEmployees(MultipartFile multipartFile) throws IOException;
}
