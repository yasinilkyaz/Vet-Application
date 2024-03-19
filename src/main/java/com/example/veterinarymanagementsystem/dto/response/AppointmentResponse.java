package com.example.veterinarymanagementsystem.dto.response;

import com.example.veterinarymanagementsystem.entities.Animal;
import com.example.veterinarymanagementsystem.entities.Doctor;

import java.time.LocalDateTime;

public class AppointmentResponse {
    private Long id;
    private LocalDateTime appointmentDate;
    private Doctor doctor;
    private Animal animal;

    public AppointmentResponse() {
    }

    public AppointmentResponse(Long id, LocalDateTime appointmentDate, Doctor doctor, Animal animal) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.doctor = doctor;
        this.animal = animal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
