package com.example.veterinarymanagementsystem.dto.response;

import com.example.veterinarymanagementsystem.entities.Animal;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;

import java.util.List;

public class CustomerResponse {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String city;

    public CustomerResponse() {
    }

    public CustomerResponse(Long id, String name, String phone, String email, String address, String city) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.city = city;
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
}
