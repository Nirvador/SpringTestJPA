package com.example.demo.controller;

import com.example.demo.bo.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Api("Employee Endpoint")
@RequestMapping("/employee")
public interface EmployeeController {

    @GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch all employees")
    List<Employee> getAllEmployee();

    @GetMapping(value = "/{id}")
    ResponseEntity<Employee> getEmployee(@PathVariable String id) throws ResourceNotFoundException;

    @PostMapping(value = "/employees")
    Employee createEmployee(@RequestBody Employee employee);

    @PutMapping("/{id}")
    ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee employeeSpecification)
            throws ResourceNotFoundException;

    @DeleteMapping("/{id}")
    HashMap<String, Boolean> deleteEmployee(@PathVariable String id) throws ResourceNotFoundException;
}
