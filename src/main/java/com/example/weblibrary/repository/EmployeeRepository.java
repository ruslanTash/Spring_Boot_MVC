package com.example.weblibrary.repository;

import DTO.EmployeeDTO;
import DTO.EmployeeFullDTO;
import com.example.weblibrary.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
