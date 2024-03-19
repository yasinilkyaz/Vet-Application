package com.example.veterinarymanagementsystem.business.abstracts;

import com.example.veterinarymanagementsystem.dto.request.VaccineRequest;
import com.example.veterinarymanagementsystem.dto.response.VaccineResponse;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {
    VaccineResponse save(VaccineRequest saveRequest);
    List<VaccineResponse> findAll();
    VaccineResponse getById(Long id);
    VaccineResponse update(Long id, VaccineRequest request);

    void deleteById(Long id);

    List<VaccineResponse> findVaccinesByProtectionDateRange(LocalDate startDate, LocalDate endDate);
    List<VaccineResponse> findVaccinesByAnimalId(Long animalId);
}
