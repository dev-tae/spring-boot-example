package com.taehyunkim.springbootexample.controller;

import com.taehyunkim.springbootexample.SpringBootExampleApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"classpath:clear-db.sql", "classpath:test-data.sql"})

public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllPersonShouldReturnOkStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/person"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void getPersonById() throws Exception {

        mockMvc.perform(get("/api/person/{id}", "f1fb5a7e-ee84-4bcf-9593-b670a5a5ef72"))
                .andExpect(status().isOk());
    }
}