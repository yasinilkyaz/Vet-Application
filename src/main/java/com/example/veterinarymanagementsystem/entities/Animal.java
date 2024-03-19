package com.example.veterinarymanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "animals")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "animal_name")
    private String name;

    @Column(name = "animal_species")
    private String species;

    @Column(name = "animal_breed")
    private String breed;

    @Column(name = "animal_gender")
    private Gender gender;

    @Column(name = "animal_colour")
    private String colour;

    @Column(name = "animal_date_of_birth")
    private LocalDate dateOfBirth;

    @JsonIgnore
    @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE)
    private List<Vaccine> vaccines;

    @JsonIgnore
    @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE)
    private List<Appointment> appointments;
    @ManyToOne
    @JoinColumn(name = "customers_id", referencedColumnName = "id")
    private Customer customer;


    public Animal() {
    }

    public Animal(Long id, String name, String species, String breed, Gender gender, String colour, LocalDate dateOfBirth, List<Vaccine> vaccines, List<Appointment> appointments, Customer customer) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.colour = colour;
        this.dateOfBirth = dateOfBirth;
        this.vaccines = vaccines;
        this.appointments = appointments;
        this.customer = customer;
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Vaccine> getVaccines() {
        return vaccines;
    }

    public void setVaccines(List<Vaccine> vaccines) {
        this.vaccines = vaccines;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
