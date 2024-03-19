package com.example.veterinarymanagementsystem.dto.response;

import com.example.veterinarymanagementsystem.entities.Customer;
import com.example.veterinarymanagementsystem.entities.Gender;
import com.example.veterinarymanagementsystem.entities.Vaccine;

import java.time.LocalDate;
import java.util.List;

public class AnimalResponse {
    private Long id;
    private String name;
    private String species;
    private String breed;
    private Gender gender;
    private String colour;
    private LocalDate dateOfBirth;



    public AnimalResponse() {
    }

    public AnimalResponse(Long id, String name, String species, String breed, Gender gender, String colour, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.colour = colour;
        this.dateOfBirth = dateOfBirth;

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

}
