package com.example.veterinarymanagementsystem.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "available_dates")
public class AvailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "available_date")
    private LocalDate availableDate;

    @ManyToOne
    @JoinColumn(name = "doctor_id",referencedColumnName = "id")
    private Doctor doctor;

    public AvailableDate() {
    }

    public AvailableDate(Long id, LocalDate availableDate, Doctor doctor) {
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
