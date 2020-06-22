package com.example.demo.controller.impl;

import com.example.demo.DemoApplication;
import com.example.demo.bo.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.HttpClientErrorException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeControllerImplTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getUrl() {
        return "http://localhost:" + port + "/demo/v1/employee";
    }

    @Test
    @Order(1)
    @DisplayName("IT - Fetch All Employees")
    public void EmployeeController_shouldGetAllEmployees() {
        // Given
        String expectedContent = "[{\"id\":1,\"name\":\"Stark\",\"age\":35},{\"id\":2,\"name\":\"Banner\",\"age\":33}]";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        // When
        ResponseEntity<String> response = testRestTemplate.exchange(getUrl() + "/employees",
                HttpMethod.GET, entity, String.class);

        // Then
        assertThat(response.getBody()).isEqualTo(expectedContent);
    }

    @Test
    @Order(2)
    @DisplayName("IT - Get Employee By id")
    public void EmployeeController_shouldHaveEmployeeById() {
        // Given

        // When
        Employee employee = testRestTemplate.getForObject(getUrl() + "/1", Employee.class);

        // Then
        assertThat(employee).extracting("name").isEqualTo("Stark");
    }

    @Test
    @Order(3)
    @DisplayName("IT - create Employee")
    public void EmployeeController_shouldCreateEmployee() {
        // Given
        Employee employee = new Employee(3, "Hawkeye", 65);

        // When
        ResponseEntity<Employee> response = testRestTemplate.postForEntity(getUrl() + "/employees",
                employee, Employee.class);

        // Then
        assertThat(response.getBody()).isEqualToComparingFieldByField(employee);
    }

    @Test
    @Order(4)
    @DisplayName("IT - update Employee")
    public void EmployeeController_shouldUpdateEmployee() {
        // Given
        Employee employee = testRestTemplate.getForObject(getUrl() + "/1", Employee.class);
        employee.setName("Thor");
        employee.setAge(100);

        // When
        testRestTemplate.put(getUrl() + "/1", employee);
        Employee updatedEmployee = testRestTemplate.getForObject(getUrl() + "/1", Employee.class);

        // Then
        assertThat(updatedEmployee).extracting("name", "age").containsOnly("Thor", 100);
    }

    @Test
    @Order(5)
    @DisplayName("IT - delete Employee")
    public void EmployeeController_shouldDeleteEmployee() {
        // Given
        Employee employee = testRestTemplate.getForObject(getUrl() + "/2", Employee.class);
        assertThat(employee).hasNoNullFieldsOrProperties();

        // When
        testRestTemplate.delete(getUrl() + "/2");

        // Then
        try {
           testRestTemplate.getForObject(getUrl() + "/2", Employee.class);
        } catch (HttpClientErrorException e) {
            assertThat(e.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }

    }
}