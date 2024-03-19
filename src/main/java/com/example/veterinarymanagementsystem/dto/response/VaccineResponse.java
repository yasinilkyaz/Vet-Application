package com.example.veterinarymanagementsystem.dto.response;

import java.time.LocalDate;

public class VaccineResponse {

    private Long id;
    private String name;
    private String code;
    private LocalDate protectionStartDate;
    private  LocalDate protectionFinishDate;
    private Long reportId;
    private AnimalResponse animal;

    public VaccineResponse() {
    }

    public VaccineResponse(Long id, String name, String code, LocalDate protectionStartDate, LocalDate protectionFinishDate, AnimalResponse animal, Long reportId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.protectionStartDate = protectionStartDate;
        this.protectionFinishDate = protectionFinishDate;
        this.animal = animal;
        this.reportId = reportId;
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

    public AnimalResponse getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalResponse animal) {
        this.animal = animal;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }
}
