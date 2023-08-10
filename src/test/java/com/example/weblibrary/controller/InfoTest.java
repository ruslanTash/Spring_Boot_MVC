package com.example.weblibrary.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@SpringBootTest
@AutoConfigureMockMvc
public class InfoTest {



    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", roles = "USER", password = "1234")
    void getAppInfo() throws Exception {
        mockMvc.perform(get("/appInfo/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("test"));
    }
}

