package com.example.veterinarymanagementsystem.dto.response;


public class ReportResponse {
    private Long id;
    private String title;
    private String diagnosis;
    private double price;
    private Long animalId;


    public ReportResponse() {
    }

    public ReportResponse(Long id, String title, String diagnosis, double price, Long animalId) {
        this.id = id;
        this.title = title;
        this.diagnosis = diagnosis;
        this.price = price;
        this.animalId = animalId;

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


}
