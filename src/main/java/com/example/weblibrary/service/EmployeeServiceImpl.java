package com.example.weblibrary.service;

import com.example.weblibrary.DTO.EmployeeDTO;
import com.example.weblibrary.DTO.EmployeeFullDTO;
import com.example.weblibrary.exceptions.EmployeeNotFoundException;
import com.example.weblibrary.exceptions.ReportNotFoundException;
import com.example.weblibrary.model.Report;
import com.example.weblibrary.repository.PagingEmployeeRepository;
import com.example.weblibrary.repository.ReportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);


    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employeeList = (List<Employee>) employeeRepository.findAll();
        logger.info("Вызван метод getAllEmployees()");
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
        logger.info("Сотруднику с id " + id + " установлена зарплата = " + newSalary);
    }

    @Override
    public EmployeeDTO getEmplpyeeById(int id) {
        EmployeeDTO employeeDTO = EmployeeDTO.fromEmployee(employeeRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Сотрудник с ID {} не найден", id);
                    return new EmployeeNotFoundException(id);
                }));
        logger.info("Запрошен сотрудник с id = " + id);
        return employeeDTO;
    }

    @Override
    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
        logger.info("Сотрудник с ID " + id + "удалён");
    }

    @Override
    public List<EmployeeDTO> findEmployeeBySalaryGreaterThan(int lowerBorder) {
        List<EmployeeDTO> employees = getAllEmployees().stream()
                .filter(e -> e.getSalary() > lowerBorder)
                .toList();
        logger.info("Запрошены сотрудники с зарплатой большей чем {}", lowerBorder);
        return employees;
    }

    @Override
    public List<EmployeeDTO> getEmployeeWithHighestSalary() {
        List<EmployeeDTO> employees = getAllEmployees().stream()
                .filter(e -> e.getSalary() == getMaxSalary())
                .toList();
        logger.info("Запрошен сотрудник/сотрудники с максимальной зарплатой");
        return employees;
    }

    @Override
    public List<EmployeeFullDTO> getEmplpoyeeByPosition(Integer positionId) {
        long count = getAllFullEmployees().stream()
                .filter(e -> e.getPosition().getPositionId().equals(positionId))
                .count();
        if (count != 0) {
            logger.info("Запрос сотрудников с positionId = " + positionId);
            return getAllFullEmployees().stream()
                    .filter(e -> e.getPosition().getPositionId().equals(positionId))
                    .toList();
        } else {
            logger.info("Cотрудников с positionId = " + positionId + " не нашлось" +
                    "Выведена информация обо всех сотрудниках");
            return getAllFullEmployees();
        }
    }

    @Override
    public EmployeeFullDTO getEmployeeFullInfoById(Integer id) {
        EmployeeFullDTO employeeFullDTO = EmployeeFullDTO.fromEmployee(employeeRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Сотрудник с ID {} не найден", id);
                    return new EmployeeNotFoundException(id);
                }));
        logger.info("Запрошена fullInfo сотрудника id = " + id);
        return employeeFullDTO;
    }

    @Override
    public List<EmployeeDTO> getEmployeeByPage(int pageIndex) {
        int unitPerPage = 10;
        Pageable employeeOfConcretePage = PageRequest.of(pageIndex, unitPerPage);
        Page<Employee> page = pagingEmployeeRepository.findAll(employeeOfConcretePage);
        List<EmployeeDTO> employeeDTOList = page.stream()
                .map(EmployeeDTO::fromEmployee)
                .toList();
        logger.info("Вызван метод getEmployeeByPage, pageIndex = " + pageIndex);
        return employeeDTOList;
    }

    @Override
    public void uploadEmployees(MultipartFile multipartFile) throws IOException {
        File file = new File("new.json");
        Files.write(file.toPath(), multipartFile.getBytes());
        ObjectMapper objectMapper = new ObjectMapper();
        Employee employee = objectMapper.readValue(file, Employee.class);
        logger.info("Вызван метод uploadEmployees");
        employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeFullDTO> getAllFullEmployees() {
        List<Employee> employeeList = (List<Employee>) employeeRepository.findAll();
        List<EmployeeFullDTO> employeeFullDTOList = employeeList.stream().
                map(EmployeeFullDTO::fromEmployee)
                .toList();
        logger.info("Вызван метод getAllFullEmployees()");
        return employeeFullDTOList;
    }

    private Integer getMaxSalary() {
        return getAllEmployees().stream()
                .map(e -> e.getSalary())
                .max(Comparator.naturalOrder()).orElse(null);
    }


}