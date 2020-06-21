package com.example.demo.controller.impl;

import com.example.demo.bo.Employee;
import com.example.demo.controller.EmployeeController;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * Default implementation of the {@link EmployeeController}
 */
@RestController
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeControllerImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> getAllEmployee() {
        return ImmutableList.copyOf(employeeRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Employee> getEmployee(String id) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for the id " + id));
        return ResponseEntity.ok().body(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Employee> updateEmployee(String id, Employee employeeSpecification)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for the id " + id));

        employee.setName(employeeSpecification.getName());
        employee.setAge(employeeSpecification.getAge());

        final Employee employeeUpdated = employeeRepository.save(employee);
        return ResponseEntity.ok(employeeUpdated);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HashMap<String, Boolean> deleteEmployee(String id) throws ResourceNotFoundException {
        HashMap<String, Boolean> response = new HashMap<>();
        Employee employee = employeeRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for the id " + id));

        employeeRepository.delete(employee);
        response.put("deleted", true);
        return response;

    }
}
