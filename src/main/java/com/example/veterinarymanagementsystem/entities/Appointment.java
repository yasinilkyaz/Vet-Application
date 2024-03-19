package com.example.veterinarymanagementsystem.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "appointment_date")
    private LocalDateTime appointmentDate;

    @ManyToOne
    @JoinColumn(name = "doctors_id",referencedColumnName = "id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "animals_id", referencedColumnName = "id")
    private Animal animal;

    @OneToOne(mappedBy = "appointment")
    private Report report;

    public Appointment() {
    }

    public Appointment(Long id, LocalDateTime appointmentDate, Doctor doctor, Animal animal, Report report) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.doctor = doctor;
        this.animal = animal;
        this.report = report;
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

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
