package com.example.weblibrary.service;

import DTO.EmployeeDTO;
import DTO.EmployeeFullDTO;
import DTO.PositionDTO;
import com.example.weblibrary.exceptions.EmployeeExceptions;
import com.example.weblibrary.repository.PagingEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.weblibrary.model.Employee;
import com.example.weblibrary.repository.EmployeeRepository;

import java.awt.print.Pageable;
import java.util.Comparator;
import java.util.List;


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
        getEmplpyeeById(id).setSalary(newSalary);
//        employeeRepository.save(employeeRepository.findById(id).orElse(new Employee()));
    }

    @Override
    public EmployeeDTO getEmplpyeeById(int id) {
        return EmployeeDTO.fromEmployee(employeeRepository.findById(id)
                .orElse(new Employee()));
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
                .orElse(new Employee()));
    }

    @Override
    public List<EmployeeDTO> getEmployeeByPage(int pageIndex) {
        int unitPerPage = 10;
        Pageable employeeOfConcretePage = (Pageable) PageRequest.of(pageIndex, unitPerPage);
        Page<Employee> page = pagingEmployeeRepository.findAll((org.springframework.data.domain.Pageable) employeeOfConcretePage);

        return page.stream().map(EmployeeDTO::fromEmployee)
                .toList();
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