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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Testcontainers
public class EmployeeTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private final PostgreSQLContainer<> postgres = new PostgreSQLContainer<>("postgres:latest");


    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "1234")
    void givenNoBody_whenEmptyJsonArray() throws Exception {
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
        createEmployees();
        mockMvc.perform(get("/employees/withHighestSalary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Последний"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "USER", password = "1234")
    void getEmployeeWithsalaryHigherThan_Test() throws Exception {
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
        mockMvc.perform(get("/employees/{id}", 10))
                .andExpect(status().isNotFound());
        mockMvc.perform(get("/employees/{id}/fullInfo", 10))
                .andExpect(status().isNotFound());
    }


    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "1234")
    void addEmployeeFromFile() throws Exception {
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
