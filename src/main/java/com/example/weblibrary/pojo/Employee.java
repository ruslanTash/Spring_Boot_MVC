package com.example.weblibrary.pojo;

import lombok.*;
import org.springframework.context.annotation.Primary;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

public class Employee {
    private int id;
    private String name;
    private int salary;
}
