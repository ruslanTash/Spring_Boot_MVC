package com.example.weblibrary.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.example.weblibrary.model.Employee;
import com.example.weblibrary.repository.EmployeeRepository;
import com.example.weblibrary.service.ReportService;
import com.example.weblibrary.model.Position;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import javax.sql.DataSource;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class ReportTest {

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withUsername("postgres")
            .withPassword("8258");

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;


    @Autowired
    private ReportService reportService;

    @Test
    @WithMockUser(username = "admin", roles = "USER", password = "1234")
    void getReport() throws Exception {
        createEmployees();
        mockMvc.perform(post("/report"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = "USER", password = "1234")
    void getReportById() throws Exception {
        Integer id = reportService.createReports();
        mockMvc.perform(get("/report/{id}", id))
                .andExpect(status().isOk());
    }

    void createEmployees() {
        List<Employee> employeeList = List.of(
                new Employee(1, "Первый", 10000, new Position(1, "Позишен №1")),
                new Employee(1, "Второй", 20000, new Position(1, "Позишен №1")),
                new Employee(1, "Последний", 30000, new Position(2, "Позишен №2"))
        );
        employeeRepository.saveAll(employeeList);
    }
}
