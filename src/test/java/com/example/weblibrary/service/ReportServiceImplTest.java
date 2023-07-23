package com.example.weblibrary.service;

import com.example.weblibrary.exceptions.ReportNotFoundException;
import com.example.weblibrary.model.Report;
import com.example.weblibrary.repository.ReportRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {

    @Mock
    private ReportRepository reportRepositoryMock;
    @InjectMocks
    private ReportServiceImpl reportServiceMock;

    ObjectMapper objectMapper = new ObjectMapper();

    private final Report REPORT_1 = new Report(1, "Первый отчёт", "путь_1");
    private final Report REPORT_2 = new Report(2, "Второй отчёт", "путь_2");
    private final Report REPORT_3 = new Report(3, "Последний отчёт", "путь_3");
    private final List<Report> REPORT_LIST = new ArrayList<>(List.of(REPORT_1, REPORT_2, REPORT_3));


    @Test
    void getReportById() {
        when(reportRepositoryMock.findById(1)).thenReturn(Optional.of(REPORT_1));
        String actual = reportServiceMock.getReportById(1);
        String excepted = REPORT_1.getPathFile();
        assertEquals(excepted, actual);
    }

    @Test
    void getReportById_Trows_ReportNotFoundException() {
        when(reportRepositoryMock.findById(10)).thenThrow(ReportNotFoundException.class);
        assertThrows(ReportNotFoundException.class, () -> reportServiceMock.getReportById(10));
    }

    @Test
    void createReports() {
        when(reportRepositoryMock.createReport())
                .thenReturn(REPORT_LIST);
        assertEquals(null, reportServiceMock.createReports());
        verify(reportRepositoryMock, times(1)).createReport();
    }

}