package com.workintech.s19d4.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.s19d4.entity.Employee;
import com.workintech.s19d4.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;


    @Test
    void save() throws Exception {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setEmail("name@mail.com");
        employee.setFirstName("name");
        employee.setLastName("surname");
        employee.setSalary(35000d);

        when(employeeService.save(employee)).thenReturn(employee);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(employee))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(35000d))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
        verify(employeeService).save(employee);
    }

    @Test
    void findByOrder() throws Exception {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setEmail("name@mail.com");
        employee.setFirstName("name");
        employee.setLastName("surname");
        employee.setSalary(35000d);
        employeeList.add(employee);

        when(employeeService.findByOrder()).thenReturn(employeeList);

        mockMvc.perform(get("/employees/byOrder"))
                .andExpect(status().isOk());
        verify(employeeService).findByOrder();
    }



    private static String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
