package com.example.veterinarymanagementsystem.mapper;

import com.example.veterinarymanagementsystem.dto.request.ReportRequest;
import com.example.veterinarymanagementsystem.dto.response.ReportResponse;
import com.example.veterinarymanagementsystem.entities.Report;
import org.mapstruct.Mapper;

import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface ReportMapper {
    Report asEntity(ReportRequest reportRequest);

    ReportResponse asOutput(Report report);

    List<ReportResponse> asOutput(List<Report> reports);

    void update(@MappingTarget Report report, ReportRequest reportRequest);



}