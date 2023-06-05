package com.example.weblibrary.pojo;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Primary;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int salary;
}
