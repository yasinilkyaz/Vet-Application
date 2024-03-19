package com.example.veterinarymanagementsystem.business.abstracts;

import com.example.veterinarymanagementsystem.dto.request.ReportRequest;
import com.example.veterinarymanagementsystem.dto.request.ReportUpdateRequest;
import com.example.veterinarymanagementsystem.dto.response.AppointmentResponse;
import com.example.veterinarymanagementsystem.dto.response.ReportResponse;


import java.util.List;

public interface IReportService {
    ReportResponse save(ReportRequest reportRequest);
    List<ReportResponse> findAll();
    ReportResponse getById(Long id);
    ReportResponse update(Long id, ReportUpdateRequest reportRequest);

    void deleteById(Long id);
    List<AppointmentResponse> findUnreportedAppointments();
}
