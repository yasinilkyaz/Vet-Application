package com.example.veterinarymanagementsystem.repository;

import com.example.veterinarymanagementsystem.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorDao extends JpaRepository<Doctor,Long> {
    Optional<Doctor> findByNameAndAddressAndCityAndEmailAndPhone(String name,String address, String city, String email, String phone);
}
