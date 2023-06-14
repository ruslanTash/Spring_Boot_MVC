package com.example.weblibrary.DTO;

import com.example.weblibrary.model.Employee;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class EmployeeDTO implements Serializable {
    private Integer id;
    private String name;
    private Integer salary;
    private Integer department;


    public static EmployeeDTO fromEmployee(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSalary(employee.getSalary());
        return employeeDTO;
    }

    public Employee toEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setId(this.getId());
        employee.setName(this.getName());
        employee.setSalary(this.getSalary());
        return employee;
    }
}
