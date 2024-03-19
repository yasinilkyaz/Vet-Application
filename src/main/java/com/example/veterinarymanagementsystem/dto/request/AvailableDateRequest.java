package com.example.veterinarymanagementsystem.dto.request;

import java.time.LocalDate;

public class AvailableDateRequest {
    private LocalDate availableDate;
    private Long doctorId;

    public AvailableDateRequest() {
    }

    public AvailableDateRequest(LocalDate availableDate, Long doctorId) {
        this.availableDate = availableDate;
        this.doctorId = doctorId;
    }

    public LocalDate getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(LocalDate availableDate) {
        this.availableDate = availableDate;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
}
