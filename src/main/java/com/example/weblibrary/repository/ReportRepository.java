package com.example.weblibrary.repository;

import com.example.weblibrary.model.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReportRepository extends CrudRepository<Report, Integer> {

    @Query("SELECT new com.example.weblibrary.model.Report(" +
            "p.positionName, COUNT (e.id), MAX (e.salary), MIN (e.salary), AVG (e.salary)) " +
            "FROM Employee e join  fetch Position p " +
            "where e.position = p " +
            "Group by p.positionId " )
    List<Report> createReport();

}