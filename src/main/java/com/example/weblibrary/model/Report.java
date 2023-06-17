package com.example.weblibrary.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@Table(name = "report")
public class Report implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Integer id;

    @Lob
    @Column(name = "data", columnDefinition = "text")
    private String data;

    public Report(String data) {
        this.data = data;
    }

    public Report(String positionName, Long count, Integer max, Integer min, Double avg) {
        this.data = "Position: " + positionName + ", countOfEmployee: " + count + ", maxSalary: " + max + ", minSalary: " + min + ", avgSalary: " + avg;
    }
 }
