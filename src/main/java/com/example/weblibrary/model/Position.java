package com.example.weblibrary.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "position")
public class Position implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer positionId;
    private String positionName;

//    @OneToMany(mappedBy = "employee")
//    @JoinColumn(name = "employee_id")
//    private List<Employee> employees;


}
