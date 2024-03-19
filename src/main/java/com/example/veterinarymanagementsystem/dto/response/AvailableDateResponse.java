package com.example.veterinarymanagementsystem.dto.response;

import com.example.veterinarymanagementsystem.entities.Doctor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class AvailableDateResponse {

    private Long id;
    private LocalDate availableDate;

    private Doctor doctor;

    public AvailableDateResponse() {
    }

    public AvailableDateResponse(Long id, LocalDate availableDate, Doctor doctor) {
        this.id = id;
        this.availableDate = availableDate;
        this.doctor = doctor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(LocalDate availableDate) {
        this.availableDate = availableDate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}