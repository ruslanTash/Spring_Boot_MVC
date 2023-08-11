package com.example.weblibrary.controller;

import com.example.weblibrary.model.Employee;
import com.example.weblibrary.model.Position;
import com.example.weblibrary.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc

@Testcontainers
public class EmployeeTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

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

    @Test
    void testPostgresql() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            assertThat(conn).isNotNull();
        }
    }






    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "1234")
    void givenNoBody_whenEmptyJsonArray() throws Exception {
        employeeRepository.deleteAll();
        // Имитируем GET-запрос к "/user"
        mockMvc.perform(get("/admin/employees/all"))
                // Проверяем, что статус ответа — 200 (OK)
                .andExpect(status().isOk())
                // Проверяем, что тело ответа — массив
                .andExpect(jsonPath("$").isArray())
                // Проверяем, что массив пуст
                .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "1234")
    void getEmployeesTest() throws Exception {
        employeeRepository.deleteAll();
        createEmployees();
        mockMvc.perform(get("/admin/employees/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].name").value("Первый"))
                .andExpect(jsonPath("$[1].name").value("Второй"))
                .andExpect(jsonPath("$[2].name").value("Последний"));

    }

    @Test
    @WithMockUser(username = "admin", roles = "USER", password = "1234")
    void getEmployeeWithHighestSalary() throws Exception {
        employeeRepository.deleteAll();
        createEmployees();
        mockMvc.perform(get("/employees/withHighestSalary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Последний"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "USER", password = "1234")
    void getEmployeeWithsalaryHigherThan_Test() throws Exception {
        employeeRepository.deleteAll();
        createEmployees();
        mockMvc.perform(get("/employees/salaryHigherThan?salary=15000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].salary").value(20000))
                .andExpect(jsonPath("$[1].salary").value(30000));
    }


    @Test
    @WithMockUser(username = "admin", roles = "USER", password = "1234")
    void whenNotFound_getStatus404() throws Exception {
        employeeRepository.deleteAll();
        mockMvc.perform(get("/employees/{id}", 10))
                .andExpect(status().isNotFound());
        mockMvc.perform(get("/employees/{id}/fullInfo", 10))
                .andExpect(status().isNotFound());
    }


    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "1234")
    void addEmployeeFromFile() throws Exception {
        employeeRepository.deleteAll();
        createEmployees();
        Employee employee = new Employee(4, "Следующий", 40000, new Position(1, "Позишен №1"));
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(employee);
        MockMultipartFile file = new MockMultipartFile("file", "employee.json", MediaType.MULTIPART_FORM_DATA_VALUE, json.getBytes());

        mockMvc.perform(multipart("/admin/employees/upload")
                        .file(file))
                .andExpect(status().isOk());
        mockMvc.perform(get("/admin/employees/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[3].name").value("Следующий"));
    }


    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "1234")
    void deleteEmployee_Test() throws Exception {
        employeeRepository.deleteAll();
        createEmployees();
        mockMvc.perform(delete("/admin/employees/3"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/admin/employees/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Первый"))
                .andExpect(jsonPath("$[1].name").value("Второй"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "USER", password = "1234")
    void getEmployeeByPage() throws Exception {
        employeeRepository.deleteAll();
        createEmployees();
        mockMvc.perform(get("/employees/page?page=0")
                        .param("page", String.valueOf(0)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(3));
    }


    void createEmployees() {

        List<Employee> employeeList = List.of(
                new Employee(1, "Первый", 10000, new Position(1, "Позишен №1")),
                new Employee(2, "Второй", 20000, new Position(1, "Позишен №1")),
                new Employee(3, "Последний", 30000, new Position(2, "Позишен №2"))
        );
        employeeRepository.saveAll(employeeList);
    }
}
