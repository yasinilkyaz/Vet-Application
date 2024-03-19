package com.example.veterinarymanagementsystem.dto.request;



import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public class ReportRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String diagnosis;
    @NotNull
    @Min(value = 0, message = "Price must be greater than or equal to zero")
    private double price;
    @NotNull
    private Long appointmentId;


    public ReportRequest() {
    }

    public ReportRequest(String title, String diagnosis, double price, Long appointmentId) {
        this.title = title;
        this.diagnosis = diagnosis;
        this.price = price;
        this.appointmentId=appointmentId;

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

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }


}
