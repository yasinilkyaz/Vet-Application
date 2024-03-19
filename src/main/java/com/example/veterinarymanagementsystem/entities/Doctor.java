package com.example.veterinarymanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "doctor_name")
    private String name;

    @Column(name = "doctor_phone")
    private String phone;

    @Column(name = "doctor_email")
    @Email
    private String email;

    @Column(name = "doctor_address")
    private String address;

    @Column(name = "city")
    private String city;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.REMOVE)
    private List<AvailableDate> availableDates;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor" ,cascade = CascadeType.REMOVE)
    private List<Appointment> appointments;

    public Doctor() {
    }

    public Doctor(Long id, String name, String phone, String email, String address, String city, List<AvailableDate> availableDates,List<Appointment> appointments) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.city = city;
        this.availableDates = availableDates;
        this.appointments=appointments;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<AvailableDate> getAvailableDates() {
        return availableDates;
    }

    public void setAvailableDates(List<AvailableDate> availableDates) {
        this.availableDates = availableDates;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
