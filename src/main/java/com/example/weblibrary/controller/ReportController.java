package com.example.weblibrary.controller;

import com.example.weblibrary.DTO.EmployeeDTO;
import com.example.weblibrary.DTO.EmployeeFullDTO;
import com.example.weblibrary.model.Report;
import com.example.weblibrary.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.weblibrary.service.EmployeeService;

import java.util.List;


@RestController
@RequestMapping("employees")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;


    //            POST-запрос
//    localhost:8080/report
//. Он должен формировать JSON-файл со статистикой по отделам:
//    Название отдела.
//    Количество сотрудников.
//    Максимальная зарплата.
//    Минимальная зарплата.
//    Средняя зарплата.
//    А также сохранять содержимое файла в базу данных. Метод возвращает целочисленный идентификатор сохраненного в базе данных файла.
    @PostMapping("report")
    public Integer getReports(){
        return reportService.getReports();
    }
    //
//            GET-запрос
//    localhost:8080/report/{id}
//. Он должен находить и возвращать созданный ранее файл в формате JSON по переданному уникальному идентификатору.
    @GetMapping("/report/{id}")
    public Report getReportById(@PathVariable ("id") Integer id){
        return reportService.getReportById(id);
    }
}
