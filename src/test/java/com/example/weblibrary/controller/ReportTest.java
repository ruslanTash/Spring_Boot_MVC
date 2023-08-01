package com.example.weblibrary.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.example.weblibrary.repository.EmployeeRepository;
import com.example.weblibrary.service.ReportService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ReportTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;


    @Autowired
    private ReportService reportService;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "1234")
    void getReport() throws Exception {
        reportService.createReports();
        mockMvc.perform(post("/report/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(0));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "1234")
    void getReportById() throws Exception {
        Integer id = reportService.createReports();
        mockMvc.perform(get("/report/{id}", id))
                .andExpect(status().isOk());
    }
//
//    void addEmployeeListInRepository() {
//        Position position = new Position(1, "position-1");
//        Position position2 = new Position(2, "position-2");
//        positionRepository.save(position);
//        positionRepository.save(position2);
//        List<Employee> employeeList = List.of(
//                new Employee(1, "Ivan", 10000, 1, position),
//                new Employee(2, "Inna", 20000, 2, position2),
//                new Employee(3, "Anna", 30000, 3, position2)
//        );
//        employeeRepository.saveAll(employeeList);
//    }
}
