package com.example.weblibrary.service;

import com.example.weblibrary.DTO.EmployeeDTO;
import com.example.weblibrary.DTO.EmployeeFullDTO;
import com.example.weblibrary.exceptions.EmployeeNotFoundException;
import com.example.weblibrary.model.Employee;
import com.example.weblibrary.model.Position;
import com.example.weblibrary.repository.EmployeeRepository;
import com.example.weblibrary.repository.PagingEmployeeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepositoryMock;
    @Mock
    private PagingEmployeeRepository pagingEmployeeRepositoryMock;
    @InjectMocks
    private EmployeeServiceImpl employeeServiceMock;

    private static final Position POSITION1 = new Position(1, "Test1");
    private static final Position POSITION2 = new Position(2, "Test2");
    private static final Employee EMPLOYEE1 = new Employee(1, "Первый", 20000, POSITION1);
    private static final Employee EMPLOYEE2 = new Employee(2, "Второй", 25000, POSITION1);
    private static final Employee EMPLOYEE3 = new Employee(3, "Последний", 10000, POSITION2);
    private static final List<Employee> EMPLOYEE_LIST = new ArrayList<>(List.of(EMPLOYEE1,
            EMPLOYEE2,
            EMPLOYEE3));
    private static final Page<Employee> EMPLOYEE_PAGE = new PageImpl<>(EMPLOYEE_LIST);


    @Test
    @DisplayName("Получение списка всех Employees")
    void getAllEmployees_OK() {
        when(employeeRepositoryMock.findAll()).thenReturn(EMPLOYEE_LIST);
        List<EmployeeDTO> actual = employeeServiceMock.getAllEmployees();
        assertEquals(EMPLOYEE_LIST.size(), actual.size());
    }

    @Test
    @DisplayName("Получение Employee по ID")
    void getEmplpyeeById_OK() {
        when(employeeRepositoryMock.findById(1)).thenReturn(Optional.of(EMPLOYEE1));
        Optional<Employee> actual = employeeRepositoryMock.findById(1);
        assertEquals(Optional.of(EMPLOYEE1), actual);
    }

    @Test
    void getEmplpyeeById_TrowsReportNotFoundException() {
        when(employeeRepositoryMock.findById(10)).thenThrow(EmployeeNotFoundException.class);
        assertThrows(EmployeeNotFoundException.class, () -> employeeServiceMock.getEmplpyeeById(10));
    }

    @Test
    void findEmployeeBySalaryGreaterThan_OK() {
        int lowerBorder = 15000;
        when(employeeRepositoryMock.findAll()).thenReturn(EMPLOYEE_LIST);
        List<EmployeeDTO> actual = employeeServiceMock.findEmployeeBySalaryGreaterThan(lowerBorder);
        List<EmployeeDTO> excepted = List.of(EmployeeDTO.fromEmployee(EMPLOYEE1), EmployeeDTO.fromEmployee(EMPLOYEE2));
        assertEquals(excepted.size(), actual.size());
    }


    @Test
    void getEmployeeWithHighestSalary_OK() {
        when(employeeRepositoryMock.findAll()).thenReturn(EMPLOYEE_LIST);
        List<EmployeeDTO> actual = employeeServiceMock.getEmployeeWithHighestSalary();
        List<EmployeeDTO> excepted = List.of(EmployeeDTO.fromEmployee(EMPLOYEE2));
        assertEquals(excepted.size(), actual.size());
        assertEquals(excepted.get(0).getSalary(), actual.get(0).getSalary());
    }

    @Test
    void getEmplpoyeeByPosition() {
        when(employeeRepositoryMock.findAll()).thenReturn(EMPLOYEE_LIST);
        List<EmployeeFullDTO> actual = employeeServiceMock.getEmplpoyeeByPosition(1);
        List<Employee> excepted = List.of(EMPLOYEE1, EMPLOYEE2);
        assertEquals(excepted.size(), actual.size());
        assertEquals(excepted.get(0).getPosition(), actual.get(0).getPosition());
    }

    @Test
    void getEmployeeFullInfoById() {
        when(employeeRepositoryMock.findById(1)).thenReturn(Optional.of(EMPLOYEE1));
        EmployeeFullDTO actual = employeeServiceMock.getEmployeeFullInfoById(1);
        EmployeeFullDTO excepted = EmployeeFullDTO.fromEmployee(EMPLOYEE1);
        assertEquals(excepted.getName(), actual.getName());
    }

    @Test
    void getEmployeeByPage() {
        when(pagingEmployeeRepositoryMock.findAll(PageRequest.of(0, 10)))
                .thenReturn(EMPLOYEE_PAGE);
        assertEquals(EMPLOYEE_LIST.size(), employeeServiceMock.getEmployeeByPage(0).size());
    }

    @Test
    void getAllFullEmployees() {
        when(employeeRepositoryMock.findAll()).thenReturn(EMPLOYEE_LIST);
        List<EmployeeFullDTO> actual = employeeServiceMock.getAllFullEmployees();
        List<Employee> excepted = List.copyOf(EMPLOYEE_LIST);
        excepted.forEach(EmployeeFullDTO::fromEmployee);
        assertEquals(excepted.size(), actual.size());
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void findEmployeeBySalaryGreaterThan_Param_OK(int lowerBorder, int correctCount) {
        when(employeeRepositoryMock.findAll()).thenReturn(EMPLOYEE_LIST);
        List<EmployeeDTO> actual = employeeServiceMock.findEmployeeBySalaryGreaterThan(lowerBorder);
        assertEquals(correctCount, actual.size());
    }
    private static Stream<Arguments> provideParamsForTests() {
        return Stream.of(
                Arguments.of(9000, 3),
                Arguments.of(19000, 2),
                Arguments.of(24000, 1)
        );
    }


}