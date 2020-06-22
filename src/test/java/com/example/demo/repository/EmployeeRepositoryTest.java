package com.example.demo.repository;

import com.example.demo.bo.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Order(1)
    @Rollback(false)
    @DisplayName("Employee Repository can create an employee")
    public void employeeRepository_shouldCreateAnEmployee() {
        // Given
        Employee bruceBanner = new Employee(4,"Loki", 35);

        // When
        Employee employeeSaved = employeeRepository.save(bruceBanner);

        // Then
        assertThat(employeeSaved.getId()).isGreaterThan(0);
        assertThat(employeeSaved).isEqualToComparingOnlyGivenFields(bruceBanner, "name", "age");
    }

    @Test
    @Order(2)
    @DisplayName("Employee Repository can find an employee")
    public void employeeRepository_shouldFindAnEmployee() {
        // Given
        Employee tonyStark = employeeRepository.findByName("Stark");

        // Then
        assertThat(tonyStark.getName()).isEqualTo("Stark");
    }

    @Test
    @Order(3)
    @DisplayName("Employee Repository can retrieve every employees")
    public void employeeRepository_shouldListAllEmployees() {
        // Given

        // When
        List<Employee> employeeList = employeeRepository.findAll();

        // Then
        assertThat(employeeList).hasSize(3);
    }

    @Test
    @Order(4)
    @Rollback(false)
    @DisplayName("Employee Repository can update an employee")
    public void employeeRepository_shouldUpdateAnEmployee() {
        // Given
        Employee tonyStark = employeeRepository.findByName("Stark");
        tonyStark.setAge(40);

        // When
        employeeRepository.save(tonyStark);
        Employee tonyStarkUpdated = employeeRepository.findByName("Stark");

        // Then
        assertThat(tonyStarkUpdated.getAge()).isEqualTo(40);

    }

    @Test
    @Order(5)
    @Rollback(false)
    @DisplayName("Employee Repository can delete an employee")
    public void employeeRepository_shouldDeleteAnEmployee() {
        // Given
        Employee tonyStark = employeeRepository.findByName("Stark");

        // When
        employeeRepository.deleteById(tonyStark.getId());
        Employee firedEmployee = employeeRepository.findByName("Stark");

        // Then
        assertThat(firedEmployee).isNull();
    }




}
