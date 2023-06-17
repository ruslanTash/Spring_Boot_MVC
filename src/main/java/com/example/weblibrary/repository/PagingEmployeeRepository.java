package com.example.weblibrary.repository;

import com.example.weblibrary.model.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PagingEmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

}
