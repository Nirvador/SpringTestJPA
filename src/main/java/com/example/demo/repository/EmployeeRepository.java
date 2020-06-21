package com.example.demo.repository;

import com.example.demo.bo.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    Employee findByName(String name);
}
