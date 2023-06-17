package com.example.weblibrary.service;

import com.example.weblibrary.model.Report;

public interface ReportService {
    Report getReportById(Integer id);

    Integer createReports();
}
