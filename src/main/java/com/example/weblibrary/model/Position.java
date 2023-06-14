package com.example.weblibrary.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "position")
public class Position {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer positionId;
    private String positionName;

//    @OneToMany(mappedBy = "employee")
//    @JoinColumn(name = "employee_id")
//    private List<Employee> employees;


}
