package com.example.weblibrary.DTO;

import com.example.weblibrary.model.Position;
import com.example.weblibrary.model.Report;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Setter
@Getter
@NoArgsConstructor
public class ReportDTO implements Serializable {
    private Integer id;
    private String data;

    public static ReportDTO fromReport(Report report){
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setId(report.getId());
        reportDTO.setData(report.getData());
        return reportDTO;
    }


    public Report toReport(ReportDTO reportDTO){
        Report report = new Report();
        report.setId(this.getId());
        report.setData(this.getData());
        return report;
    }

    public ReportDTO(String positionName, Integer max, Integer min, Integer avg) {
        this.data = positionName + ": " + max + " " +  min + " " + avg;
    }
}
