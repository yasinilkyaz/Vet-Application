package com.example.veterinarymanagementsystem.repository;

import com.example.veterinarymanagementsystem.entities.Customer;
import com.example.veterinarymanagementsystem.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDao extends JpaRepository<Customer,Long> {
    Optional<Customer> findByNameAndAddressAndCityAndEmailAndPhone(String name,String address,String city,String email,String phone);
}
