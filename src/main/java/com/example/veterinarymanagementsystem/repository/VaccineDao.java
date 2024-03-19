package com.example.veterinarymanagementsystem.repository;

import com.example.veterinarymanagementsystem.entities.Animal;
import com.example.veterinarymanagementsystem.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineDao extends JpaRepository<Vaccine,Long> {
    Optional<Vaccine> findByNameAndCodeAndProtectionStartDateAndProtectionFinishDateAndAnimal(String name, String code, LocalDate startDate, LocalDate endDate, Animal animal);

    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);
    List<Vaccine> findByAnimal(Animal animal);
}
