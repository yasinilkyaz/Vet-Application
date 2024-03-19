package com.example.veterinarymanagementsystem.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "report_title")
    private String title;
    @Column(name = "report_diagnosis")
    private String diagnosis;
    @Column(name = "report_price")
    private double price;

    @Column(name ="animal_id")
    private Long animalId;
    @OneToMany(mappedBy = "report")
    private List<Vaccine> vaccines;

    @OneToOne
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private Appointment appointment;

    public Report() {
    }

    public Report(Long id, String title, String diagnosis, double price,Long animalId, List<Vaccine> vaccines, Appointment appointment) {
        this.id = id;
        this.title = title;
        this.diagnosis = diagnosis;
        this.price = price;
        this.animalId=animalId;
        this.vaccines = vaccines;
        this.appointment = appointment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public List<Vaccine> getVaccines() {
        return vaccines;
    }

    public void setVaccines(List<Vaccine> vaccines) {
        this.vaccines = vaccines;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
