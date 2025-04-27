package com.workintech.s19d4.repository;

import com.workintech.s19d4.entity.Employee;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EmployeeRepositoryTest {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeRepositoryTest(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @BeforeEach
    void setUp() {
        Employee employee1 = new Employee();
        employee1.setFirstName("name1");
        employee1.setLastName("surname1");
        employee1.setEmail("name1@mail.com");
        employee1.setSalary(30000d);

        Employee employee2 = new Employee();
        employee2.setFirstName("name2");
        employee2.setLastName("surname2");
        employee2.setEmail("name2@mail.com");
        employee2.setSalary(20000d);

        Employee employee3 = new Employee();
        employee3.setFirstName("name3");
        employee3.setLastName("surname3");
        employee3.setEmail("name3@name.com");
        employee3.setSalary(50000d);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        employeeRepository.saveAll(employees);
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("AFTER All");
    }

    @Test
    @DisplayName(value = "find employee by email user tests")
    void findByEmail() {
        String nonExistEmail = "nonexist@mail.com";
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(nonExistEmail);
        assertEquals(Optional.empty(), employeeOptional);

        String existEmail = "name2@mail.com";
        Optional<Employee> employeeOptionalExist = employeeRepository.findByEmail(existEmail);
        assertNotNull(employeeOptionalExist.get());
        assertEquals("name2", employeeOptionalExist.get().getFirstName());
        assertEquals(20000d, employeeOptionalExist.get().getSalary());
    }

    @Test
    void findBySalary() {
        List<Employee> employees = employeeRepository.findBySalary(25000d);
        assertEquals(2, employees.size());
        assertEquals("name1",employees.get(1).getFirstName());
        assertEquals("name3",employees.get(0).getFirstName());
    }

    @Test
    void findByOrder() {
        List<Employee> employees = employeeRepository.findByOrder();
        assertEquals(3,employees.size());
        assertEquals("name1",employees.get(0).getFirstName());
        assertEquals("name2",employees.get(1).getFirstName());
        assertEquals("name3",employees.get(2).getFirstName());
    }
}
