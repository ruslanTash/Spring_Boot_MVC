package com.example.weblibrary.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Integer id;
    //    Название отдела.
    @Column(name = "department_name")
    private String departmentName;
    //    Количество сотрудников.
    @Column(name = "employee_count")
    private int employeeCount;
    //    Максимальная зарплата.
    @Column(name = "max_salary")
    private Integer maxSalary;
    //    Минимальная зарплата.
    @Column(name = "min_salary")
    private Integer minSalary;
    //    Средняя зарплата.
    @Column(name = "average_salary")
    private Integer averageSalary;
}
