package com.example.demo.controller.impl;

import com.example.demo.bo.Employee;
import com.example.demo.controller.EmployeeController;
import com.example.demo.repository.EmployeeRepository;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
}
