package com.example.veterinarymanagementsystem.dto.request;

import java.time.LocalDate;

public class FilteredAppointmentsRequest {
    private LocalDate startDate;

    private LocalDate endDate;

    private Long id;

    public FilteredAppointmentsRequest() {
    }

    public FilteredAppointmentsRequest(LocalDate startDate, LocalDate endDate, Long id) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
