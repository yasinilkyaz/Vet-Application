package com.example.veterinarymanagementsystem.repository;

import com.example.veterinarymanagementsystem.entities.Animal;
import com.example.veterinarymanagementsystem.entities.Appointment;
import com.example.veterinarymanagementsystem.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentDao extends JpaRepository<Appointment,Long> {
    Optional<Appointment> findByAppointmentDateAndDoctorAndAnimal(LocalDateTime dateTime, Doctor doctor, Animal animal);

    Optional<Appointment> findByAppointmentDateAndDoctor(LocalDateTime appointmentDateTime, Doctor doctor);
    List<Appointment> findByAppointmentDateBetweenAndDoctor(
            LocalDateTime startDateTime,
            LocalDateTime endDateTime,
            Doctor doctor
    );
    List<Appointment> findByAppointmentDateBetweenAndAnimal(LocalDateTime startDate, LocalDateTime endDate, Animal animal);

    List<Appointment> findByAppointmentDateAndAnimal(LocalDateTime appointmentDateTime, Animal animal);
}
