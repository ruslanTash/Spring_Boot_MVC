package com.example.weblibrary.DTO;

import com.example.weblibrary.model.Employee;
import com.example.weblibrary.model.Position;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeFullDTO {
    private Integer id;
    private String name;
    private Integer salary;
    private Position position;

    public static EmployeeFullDTO fromEmployee(Employee employee){
        EmployeeFullDTO employeeFullDTO = new EmployeeFullDTO();
        employeeFullDTO.setId(employee.getId());
        employeeFullDTO.setName(employee.getName());
        employeeFullDTO.setSalary(employee.getSalary());
        employeeFullDTO.setPosition(employee.getPosition());
        return employeeFullDTO;
    }

    public Employee toEmployee(EmployeeFullDTO employeeFullDTO){
        Employee employee = new Employee();
        employee.setId(this.getId());
        employee.setName(this.getName());
        employee.setSalary(this.getSalary());
        employee.setPosition(this.getPosition());
        return employee;
    }
}
