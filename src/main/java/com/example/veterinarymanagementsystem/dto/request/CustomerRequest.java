package com.example.veterinarymanagementsystem.dto.request;

public class CustomerRequest {

    private String name;
    private String phone;
    private String email;
    private String address;
    private String city;

    public CustomerRequest() {
    }

    public CustomerRequest(String name, String phone, String email, String address, String city) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.city = city;
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
