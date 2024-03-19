package com.example.veterinarymanagementsystem.dto.request;

import java.time.LocalDate;

public class VaccineDateRangeRequest {
    private LocalDate startDate;

    private LocalDate endDate;

    public VaccineDateRangeRequest() {
    }

    public VaccineDateRangeRequest(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
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
}
