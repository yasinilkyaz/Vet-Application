package com.example.veterinarymanagementsystem.dto.request;

import com.example.veterinarymanagementsystem.entities.Animal;
import com.example.veterinarymanagementsystem.entities.Doctor;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppointmentRequest {
    private LocalDate appointmentDate;
    private int appointmentHour;
    private Long doctorId;
    private Long animalId;

    public AppointmentRequest() {
    }

    public AppointmentRequest(LocalDate appointmentDate, int appointmentHour, Long doctorId, Long animalId) {
        this.appointmentDate = appointmentDate;
        this.appointmentHour = appointmentHour;
        this.doctorId = doctorId;
        this.animalId = animalId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public int getAppointmentHour() {
        return appointmentHour;
    }

    public void setAppointmentHour(int appointmentHour) {
        this.appointmentHour = appointmentHour;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }
}
