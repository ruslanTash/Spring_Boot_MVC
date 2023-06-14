package com.example.weblibrary.repository;

import com.example.weblibrary.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
