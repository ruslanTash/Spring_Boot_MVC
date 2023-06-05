package com.example.weblibrary.repository;

import com.example.weblibrary.pojo.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    public List<Employee> getAllEmployees();
}
