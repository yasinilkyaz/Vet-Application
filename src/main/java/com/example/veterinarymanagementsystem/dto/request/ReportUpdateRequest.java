package com.example.veterinarymanagementsystem.dto.request;

public class ReportUpdateRequest {
    private String title;
    private String diagnosis;
    private double price;

    public ReportUpdateRequest() {
    }

    public ReportUpdateRequest(String title, String diagnosis, double price) {
        this.title = title;
        this.diagnosis = diagnosis;
        this.price = price;
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
}
