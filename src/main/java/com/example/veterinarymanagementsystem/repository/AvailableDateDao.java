package com.example.veterinarymanagementsystem.repository;

import com.example.veterinarymanagementsystem.entities.AvailableDate;
import com.example.veterinarymanagementsystem.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AvailableDateDao extends JpaRepository<AvailableDate,Long> {
    Optional<AvailableDate> findByAvailableDateAndDoctor(LocalDate availableDate, Doctor doctor);
}
