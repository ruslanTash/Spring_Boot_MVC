package com.example.weblibrary.service;

import com.example.weblibrary.DTO.EmployeeDTO;
import com.example.weblibrary.DTO.EmployeeFullDTO;
import com.example.weblibrary.exceptions.EmployeeNotFoundException;
import com.example.weblibrary.exceptions.ReportNotFoundException;
import com.example.weblibrary.model.Report;
import com.example.weblibrary.repository.PagingEmployeeRepository;
import com.example.weblibrary.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.weblibrary.model.Employee;
import com.example.weblibrary.repository.EmployeeRepository;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PagingEmployeeRepository pagingEmployeeRepository;


    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employeeList = (List<Employee>) employeeRepository.findAll();
        return employeeList.stream().
                map(EmployeeDTO::fromEmployee)
                .toList();
    }

    @Override
    public void setSalaryById(int id, int newSalary) {
        EmployeeDTO employeeDTO = getEmplpyeeById(id);
        employeeDTO.setSalary(newSalary);
        Employee employee = employeeDTO.toEmployee(employeeDTO);
        employeeRepository.save(employee);
    }

    @Override
    public EmployeeDTO getEmplpyeeById(int id) {
        return EmployeeDTO.fromEmployee(employeeRepository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException(id)));
    }

    @Override
    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> findEmployeeBySalaryGreaterThan(int lowerBorder) {
        return getAllEmployees().stream()
                .filter(e -> e.getSalary() > lowerBorder)
                .toList();
    }

    @Override
    public List<EmployeeDTO> getEmployeeWithHighestSalary() {
        return getAllEmployees().stream()
                .filter(e -> e.getSalary() == getMaxSalary())
                .toList();
    }

    @Override
    public List<EmployeeFullDTO> getEmplpoyeeByPosition(Integer positionId) {
        long count = getAllFullEmployees().stream()
                .filter(e -> e.getPosition().getPositionId().equals(positionId))
                .count();
        if (count != 0) {
            return getAllFullEmployees().stream()
                    .filter(e -> e.getPosition().getPositionId().equals(positionId))
                    .toList();
        } else {
            return getAllFullEmployees();
        }
    }

    @Override
    public EmployeeFullDTO getEmployeeFullInfoById(Integer id) {
        return EmployeeFullDTO.fromEmployee(employeeRepository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException(id)));
    }

    @Override
    public List<EmployeeDTO> getEmployeeByPage(int pageIndex) {
        int unitPerPage = 10;
        Pageable employeeOfConcretePage = PageRequest.of(pageIndex, unitPerPage);
        Page<Employee> page = pagingEmployeeRepository.findAll(employeeOfConcretePage);
        return page.stream()
                .map(EmployeeDTO::fromEmployee)
                .toList();
    }

    @Override
    public void uploadEmployees(MultipartFile multipartFile) throws IOException {
        File file = new File("new.json");
        Files.write(file.toPath(), multipartFile.getBytes());
    }

    @Override
    public List<EmployeeFullDTO> getAllFullEmployees() {
        List<Employee> employeeList = (List<Employee>) employeeRepository.findAll();
        return employeeList.stream().
                map(EmployeeFullDTO::fromEmployee)
                .toList();
    }

    private Integer getMaxSalary() {
        return getAllEmployees().stream()
                .map(e -> e.getSalary())
                .max(Comparator.naturalOrder()).orElse(null);
    }


}