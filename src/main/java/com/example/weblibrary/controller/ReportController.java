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

    @PostMapping
    public Integer createReport() {
        return reportService.createReports();
    }


    @GetMapping("/{id}")
    public String getReportById(@PathVariable("id") Integer id) {
        return reportService.getReportById(id);
    }
}
