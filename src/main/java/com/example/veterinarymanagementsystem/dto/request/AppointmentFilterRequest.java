package com.example.veterinarymanagementsystem.dto.request;

import java.time.LocalDateTime;

public class AppointmentFilterRequest {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Long doctorId;

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
}