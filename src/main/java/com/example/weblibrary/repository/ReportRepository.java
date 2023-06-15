package com.example.weblibrary.repository;

import com.example.weblibrary.DTO.ReportDTO;
import com.example.weblibrary.model.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReportRepository extends CrudRepository<ReportDTO, Integer> {
    @Query("SELECT new com.example.weblibrary.DTO.ReportDTO(" +
            " p.positionName," +
            " max(e.salary)," +
            " min(e.salary)," +
            " avg(e.salary))" +
            " FROM Employee e join fetch Position p " +
            " WHERE e.position = p" +
            " GROUP BY p.positionId")
    List<ReportDTO> createReport();
}
