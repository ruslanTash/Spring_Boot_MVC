package com.example.weblibrary.service;

import com.example.weblibrary.exceptions.ReportNotFoundException;
import com.example.weblibrary.model.Report;
import com.example.weblibrary.repository.EmployeeRepository;
import com.example.weblibrary.repository.ReportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Report getReportById(Integer id) {
        return reportRepository.findById(id)
                .orElseThrow(()-> new ReportNotFoundException(id));
    }

    @Override
    public Integer createReports() {
        List<Report> reportDTOS = reportRepository.createReport();
        try {
            String json = objectMapper.writeValueAsString(reportDTOS);
            Report report = new Report(json);
            reportRepository.save(report);
            return report.getId();
        }  catch (IOException e) {
            e.printStackTrace();
        } return 0;
    }


}
