package com.example.veterinarymanagementsystem.business.abstracts;

import com.example.veterinarymanagementsystem.dto.request.DoctorRequest;
import com.example.veterinarymanagementsystem.dto.response.DoctorResponse;

import java.util.List;

public interface IDoctorService {
    DoctorResponse save(DoctorRequest saveRequest);
    List<DoctorResponse> findAll();
    DoctorResponse getById(Long id);
    DoctorResponse update(Long id, DoctorRequest request);

    void deleteById(Long id);
}
