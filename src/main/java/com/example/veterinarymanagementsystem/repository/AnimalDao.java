package com.example.veterinarymanagementsystem.repository;

import com.example.veterinarymanagementsystem.entities.Animal;
import com.example.veterinarymanagementsystem.entities.Customer;
import com.example.veterinarymanagementsystem.entities.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalDao extends JpaRepository<Animal, Long> {
    Optional<Animal> findByNameAndSpeciesAndBreedAndGenderAndColourAndDateOfBirthAndCustomer(String name, String species, String breed, Gender gender, String colour, LocalDate birthDay, Customer customer);

    List<Animal> findByName(String name);
    List<Animal> findByCustomerName(String ownerName);
    List<Animal> findByCustomer(Customer customer);
}
