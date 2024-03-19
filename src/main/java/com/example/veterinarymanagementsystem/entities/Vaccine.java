package com.example.veterinarymanagementsystem.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "vaccines")
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "vaccine_name")
    private String name;

    @Column(name = "vaccine_code")
    private String code;

    @Column(name = "protection_startdate")
    private LocalDate protectionStartDate;

    @Column(name = "protection_finishdate")
    private LocalDate protectionFinishDate;

    @ManyToOne
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "report_id", referencedColumnName = "id")
    private Report report;

    public Vaccine() {
    }

    public Vaccine(Long id, String name, String code, LocalDate protectionStartDate, LocalDate protectionFinishDate, Animal animal, Report report) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.protectionStartDate = protectionStartDate;
        this.protectionFinishDate = protectionFinishDate;
        this.animal = animal;
        this.report = report;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getProtectionStartDate() {
        return protectionStartDate;
    }

    public void setProtectionStartDate(LocalDate protectionStartDate) {
        this.protectionStartDate = protectionStartDate;
    }

    public LocalDate getProtectionFinishDate() {
        return protectionFinishDate;
    }

    public void setProtectionFinishDate(LocalDate protectionFinishDate) {
        this.protectionFinishDate = protectionFinishDate;
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
    public Long getReportId() {
        if (report != null) {
            return report.getId();
        }
        return null;
    }
}
