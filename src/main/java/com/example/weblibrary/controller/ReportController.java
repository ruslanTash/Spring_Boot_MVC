package com.example.weblibrary.controller;

import com.example.weblibrary.model.Report;
import com.example.weblibrary.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;


    // Он должен формировать JSON-файл со статистикой по отделам:
    // А также сохранять содержимое файла в базу данных.
    // Метод возвращает целочисленный идентификатор сохраненного в базе данных файла.
    @PostMapping
    public Integer createReport() {
        return reportService.createReports();
    }


    // Он должен находить и возвращать созданный ранее файл в формате JSON по переданному уникальному идентификатору.
    @GetMapping("/{id}")
    public Report getReportById(@PathVariable("id") Integer id) {
        return reportService.getReportById(id);
    }
}
