package com.example.weblibrary.service;

import com.example.weblibrary.exceptions.ReportNotFoundException;
import com.example.weblibrary.model.Report;
import com.example.weblibrary.repository.EmployeeRepository;
import com.example.weblibrary.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public Report getReportById(Integer id) {
        return reportRepository.findById(id)
                .orElseThrow(()-> new ReportNotFoundException(id));
    }

    @Override
    public Integer getReports() {

        return 1;
    }


}
