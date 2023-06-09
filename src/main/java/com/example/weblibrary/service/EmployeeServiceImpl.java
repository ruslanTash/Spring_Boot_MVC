package com.example.weblibrary.service;

import DTO.EmployeeDTO;
import DTO.PositionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.weblibrary.model.Employee;
import com.example.weblibrary.repository.EmployeeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;


    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employeeList = (List<Employee>) employeeRepository.findAll();
        return employeeList.stream().
                map(EmployeeDTO::fromEmployee)
                .toList();
    }

    @Override
    public void setSalaryById(int id, int newSalary) {
        getAllEmployees().stream()
                .filter(e -> e.getId() == id)
                .forEach(e -> e.setSalary(newSalary));
        employeeRepository.save(employeeRepository.findById(id).orElse(new Employee()));
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
    public List<EmployeeDTO> getEmplpoyeeByPosition(Integer positionId) {
        long count = getAllEmployees().stream()
                .filter(e -> e.getPosition().getPositionId().equals(positionId))
                .count();
        if (count != 0){
            return getAllEmployees().stream()
                    .filter(e -> e.getPosition().getPositionId().equals(positionId))
                    .toList();
        }else {
            return getAllEmployees();
        }
    }

    private Integer getMaxSalary() {
        return getAllEmployees().stream()
                .map(e -> e.getSalary())
                .max(Comparator.naturalOrder()).orElse(null);
    }

}