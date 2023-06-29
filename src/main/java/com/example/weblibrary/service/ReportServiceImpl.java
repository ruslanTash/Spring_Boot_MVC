package com.example.weblibrary.service;

import com.example.weblibrary.exceptions.ReportNotFoundException;
import com.example.weblibrary.model.Report;
import com.example.weblibrary.repository.ReportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);

    @Override
    public String getReportById(Integer id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(()-> {
                    logger.error("Отчёт с ID = " + id + " не найден");
                    return new ReportNotFoundException(id);
                });
        logger.info("Вызван метод getReportById, ReportId = " + id);
        return report.getPathFile();
    }

    @Override
    public Integer createReports() {
        List<Report> reports = reportRepository.createReport();
        try {
            String text = objectMapper.writeValueAsString(reports);
            Report report = new Report(text);
            reportRepository.save(report);
            String fileName =
                    "c:\\Users\\Ruslan\\" +
                            "report" + report.getId() + ".json";
            File file = new File(fileName);
            file.createNewFile();
            report.setPathFile(fileName);
            writeTextToFile(text, fileName);
            reportRepository.save(report);
            logger.info("Отчёт сохранён, reportId = " + report.getId());
            return report.getId();
        }  catch (IOException e) {
            logger.error("Отчёт не сформирован");
            e.printStackTrace();
        } return 0;
    }


    private static void writeTextToFile(String text, String fileName) {
        Path path = Paths.get(fileName);
        try {
            Files.write(path, text.getBytes(StandardCharsets.UTF_8));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}
