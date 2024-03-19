package com.example.veterinarymanagementsystem.api;

import com.example.veterinarymanagementsystem.business.abstracts.IReportService;
import com.example.veterinarymanagementsystem.dto.request.ReportRequest;
import com.example.veterinarymanagementsystem.dto.request.ReportUpdateRequest;
import com.example.veterinarymanagementsystem.dto.response.AppointmentResponse;
import com.example.veterinarymanagementsystem.dto.response.ReportResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/report")
public class ReportController {
    private final IReportService reportService;

    public ReportController(IReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReportResponse> findAll() {
        return reportService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReportResponse getById(@PathVariable("id") Long id) {
        return reportService.getById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ReportResponse save(@RequestBody ReportRequest reportRequest) {
        return reportService.save(reportRequest);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReportResponse update(@PathVariable("id") Long id, @RequestBody
    ReportUpdateRequest reportRequest) {
        return reportService.update(id, reportRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        reportService.deleteById(id);
    }

    @GetMapping("/unreported")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> getUnreportedAppointments() {
        return reportService.findUnreportedAppointments();
    }
}
